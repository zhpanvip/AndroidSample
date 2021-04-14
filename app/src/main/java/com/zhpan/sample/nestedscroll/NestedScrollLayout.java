package com.zhpan.sample.nestedscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

/**
 * @author zhangpan
 * @date 2021/4/14
 */
public class NestedScrollLayout extends NestedScrollView {

    private View topView;

    private ViewGroup contentView;

    public NestedScrollLayout(@NonNull Context context) {
        super(context);
    }

    public NestedScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (contentView != null) {
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            layoutParams.height = getMeasuredHeight();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewGroup parentView = (ViewGroup) getChildAt(0);
        if (parentView != null && parentView.getChildCount() == 2) {
            topView = parentView.getChildAt(0);
            contentView = (ViewGroup) parentView.getChildAt(1);
        }
    }
}
