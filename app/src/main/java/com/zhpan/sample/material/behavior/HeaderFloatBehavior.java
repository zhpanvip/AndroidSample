package com.zhpan.sample.material.behavior;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.zhpan.sample.R;

import java.lang.ref.WeakReference;

/**
 * @author zhangpan
 * @date 2021/2/3
 */
public class HeaderFloatBehavior extends CoordinatorLayout.Behavior<View> {

    private WeakReference<View> dependentView;
    private final ArgbEvaluator argbEvaluator;

    public HeaderFloatBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

        argbEvaluator = new ArgbEvaluator();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if (dependency != null && dependency.getId() == R.id.scrolling_header) {
            dependentView = new WeakReference<>(dependency);
            return true;
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Resources resources = getDependentView().getResources();
        final float progress = 1.f -
                Math.abs(dependency.getTranslationY() / (dependency.getHeight() - resources.getDimension(R.dimen.collapsed_header_height)));

        // Translation
        final float collapsedOffset = resources.getDimension(R.dimen.collapsed_float_offset_y);
        final float initOffset = resources.getDimension(R.dimen.init_float_offset_y);
        final float translateY = collapsedOffset + (initOffset - collapsedOffset) * progress;
        child.setTranslationY(translateY);

        // Background
        child.setBackgroundColor((int) argbEvaluator.evaluate(
                progress,
                resources.getColor(R.color.colorCollapsedFloatBackground),
                resources.getColor(R.color.colorInitFloatBackground)));
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        // Margins
        final int initMargin = resources.getDimensionPixelOffset(R.dimen.collapsed_float_margin);
        final float collapsedMargin = resources.getDimension(R.dimen.collapsed_header_height);
        final int margin = (int) ((collapsedMargin) * (1 - progress)) + initMargin;
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        lp.setMargins(margin, 0, margin, 0);
        child.setLayoutParams(lp);

        return true;
    }

    private View getDependentView() {
        return dependentView.get();
    }

}
