package com.zhpan.sample.material.behavior;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.zhpan.sample.R;

import java.lang.ref.WeakReference;

/**
 * @author zhangpan
 * @date 2021/2/3
 */
public class HeaderScrollingBehavior extends CoordinatorLayout.Behavior<RecyclerView> {

    private boolean isExpanded = false;
    private boolean isScrolling = false;

    private WeakReference<View> dependentView;
    private final Scroller scroller;
    private final Handler handler;

    private final Runnable flingRunnable = new Runnable() {
        @Override
        public void run() {
            if (scroller.computeScrollOffset()) {
                getDependentView().setTranslationY(scroller.getCurrY());
                handler.post(this);
            } else {
                isExpanded = getDependentView().getTranslationY() != 0;
                isScrolling = false;
            }
        }
    };

    public HeaderScrollingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
        handler = new Handler();
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        if (dependency != null && dependency.getId() == R.id.scrolling_header) {
            dependentView = new WeakReference<>(dependency);
            return true;
        }
        return false;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, RecyclerView child, int layoutDirection) {
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (lp.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
            child.layout(lp.leftMargin, -(int) (getScrollProgress(getDependentView()) * 50),
                    parent.getWidth() - lp.rightMargin, (int) (parent.getHeight() - getDependentViewCollapsedHeight()));
            return true;
        }
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        final float progress = getScrollProgress(dependency);
        child.setTranslationY(dependency.getHeight() + dependency.getTranslationY());
        float scale = 1 + 0.4f * (1.f - progress);
        dependency.setScaleX(scale);
        dependency.setScaleY(scale);
        dependency.setAlpha(progress);
        return true;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull RecyclerView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull RecyclerView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        scroller.abortAnimation();
        isScrolling = false;
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull RecyclerView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if (dy < 0) {
            return;
        }

        View dependentView = getDependentView();
        float newTranslateY = dependentView.getTranslationY() - dy;
        float minHeaderTranslate = -(dependentView.getHeight() - getDependentViewCollapsedHeight());

        if (newTranslateY > minHeaderTranslate) {
            dependentView.setTranslationY(newTranslateY);
            consumed[1] = dy;
        }
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull RecyclerView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed);
        if (dyUnconsumed > 0) {
            return;
        }

        View dependentView = getDependentView();
        float newTranslateY = dependentView.getTranslationY() - dyUnconsumed;
        final float maxHeaderTranslate = 0;

        if (newTranslateY < maxHeaderTranslate) {
            dependentView.setTranslationY(newTranslateY);
        }
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, float velocityX, float velocityY) {
        return onUserStopDragging(velocityY);
    }

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull RecyclerView child, @NonNull View target, int type) {
        if (!isScrolling) {
            onUserStopDragging(800);
        }
    }

    private boolean onUserStopDragging(float velocity) {
        View dependentView = getDependentView();
        float translateY = dependentView.getTranslationY();
        float minHeaderTranslate = -(dependentView.getHeight() - getDependentViewCollapsedHeight());

        if (translateY == 0 || translateY == minHeaderTranslate) {
            return false;
        }

        boolean targetState; // Flag indicates whether to expand the content.
        if (Math.abs(velocity) <= 800) {
            targetState = !(Math.abs(translateY) < Math.abs(translateY - minHeaderTranslate));
            velocity = 800; // Limit velocity's minimum value.
        } else {
            targetState = velocity > 0;
        }

        float targetTranslateY = targetState ? minHeaderTranslate : 0;

        scroller.startScroll(0, (int) translateY, 0, (int) (targetTranslateY - translateY), (int) (1000000 / Math.abs(velocity)));
        handler.post(flingRunnable);
        isScrolling = true;
        return true;
    }

    private float getScrollProgress(View dependencyView) {
        Resources resources = getDependentView().getResources();
        return 1.f - Math.abs(dependencyView.getTranslationY() / (dependencyView.getHeight() - resources.getDimension(R.dimen.header_height)));
    }

    private float getDependentViewCollapsedHeight() {
        return getDependentView().getResources().getDimension(R.dimen.header_height);
    }

    private View getDependentView() {
        return dependentView.get();
    }
}
