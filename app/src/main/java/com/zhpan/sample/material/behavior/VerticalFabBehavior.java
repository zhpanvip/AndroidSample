package com.zhpan.sample.material.behavior;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * @author zhangpan
 * @date 2021/1/13
 * description: FloatingActionButton跟随滑动隐藏与显示的Behavior
 */
public class VerticalFabBehavior extends FloatingActionButton.Behavior {

    private int startX, startY;
    private final int touchSlop;
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    private ObjectAnimator translationAnimator;

    private static final long ANIMATE_SCROLL_DURATION = 250;

    public VerticalFabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /**
     * 这里处理滑动Header部分时也会隐藏与显示FAB,如务必要可以删除。
     */
    @Override
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout parent,
                                         @NonNull FloatingActionButton child, @NonNull MotionEvent ev) {
        switch(ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int)ev.getX();
                startY = (int)ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                actionMove(child, ev);
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    private void actionMove(FloatingActionButton child, MotionEvent ev) {
        int endX = (int)ev.getX();
        int endY = (int)ev.getY();
        int disX = Math.abs(endX - startX);
        int disY = Math.abs(endY - startY);
        if(disY > touchSlop && disY > disX) {
            startAnimate(startY - endY, child);
        }
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull FloatingActionButton child, @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        // 需要垂直的滑动
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(
                coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed,
                               int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        // super.onNestedScroll will consume all of the unconsumed scroll distances,
        // do not do this since we are not actually consuming any distances
        onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed, type);
        if(dyConsumed != 0) {
            startAnimate(dyConsumed, child);
        }
    }

    private void startAnimate(int dyConsumed, FloatingActionButton child) {
        if(translationAnimator == null) {
            translationAnimator = ObjectAnimator.ofFloat(child, "translationY", 0,
                    child.getHeight() + getMarginBottom(child));
            translationAnimator.setDuration(ANIMATE_SCROLL_DURATION).setInterpolator(INTERPOLATOR);
        }
        if(dyConsumed > 0) {
            // 向上滑动RecyclerView,开启隐藏FloatingActionButton动画
            if(!translationAnimator.isRunning() && child.getTranslationY() <= 0) {
                translationAnimator.start();
            }
        } else if(dyConsumed < 0) {
            // 向下滑动RecyclerView，开启显示FloatingActionButton动画
            if(!translationAnimator.isRunning() && child.getTranslationY() >= child.getHeight()) {
                translationAnimator.reverse();
            }
        }
    }

    private int getMarginBottom(View v) {
        final ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if(layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return ((ViewGroup.MarginLayoutParams)layoutParams).bottomMargin;
        }
        return 0;
    }
}
