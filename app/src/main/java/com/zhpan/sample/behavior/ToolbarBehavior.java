package com.zhpan.sample.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;


/**
 * Created by zhangpan on 2020/04/11.
 */
public class ToolbarBehavior extends CoordinatorLayout.Behavior<BehaviorToolbar> {

    private int scrollY = 0;

    public ToolbarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull BehaviorToolbar child, @NonNull View directTargetChild, @NonNull View target,
                                       int axes, int type) {
        return true;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull BehaviorToolbar toolbar, @NonNull View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed, int type) {
        int endOffset = toolbar.getHeaderHeight() - toolbar.getHeight();
        scrollY += dyConsumed;
        Context context = coordinatorLayout.getContext();
        float fraction = (float)scrollY / endOffset;
        if(scrollY <= 0) {
            toolbar.setToolbarTransparent();
        } else if(scrollY < endOffset) {
            toolbar.changingColorGradually(context, fraction);
        } else if(scrollY < 2 * endOffset) {
            toolbar.setToolbarFullColor(context);
        }
    }
}
