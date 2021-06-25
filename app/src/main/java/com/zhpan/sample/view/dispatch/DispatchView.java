package com.zhpan.sample.view.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import static com.zhpan.sample.view.dispatch.DispatchActivity.TAG;
import static com.zhpan.sample.view.dispatch.DispatchActivity.getActionStr;

/**
 * <pre>
 *   Created by zhangpan on 2020/4/23.
 *   Description:
 * </pre>
 */
public class DispatchView extends View {


    public DispatchView(Context context) {
        this(context, null);
    }

    public DispatchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DispatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int id = getId();
        Log.e(TAG, "DispatchView dispatchTouchEvent" + ",Action:" + getActionStr(event.getAction()));
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "DispatchView onTouchEvent" + ",Action:" + getActionStr(event.getAction()));
        return super.onTouchEvent(event);
    }
}
