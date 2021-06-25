package com.zhpan.sample.view.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zhpan.sample.R;

import static com.zhpan.sample.view.dispatch.DispatchActivity.TAG;
import static com.zhpan.sample.view.dispatch.DispatchActivity.getActionStr;

/**
 * <pre>
 *   Created by zhangpan on 2020/4/23.
 *   Description:
 * </pre>
 */
public class DispatchLayout extends FrameLayout {
    private String idStr;

    public DispatchLayout(@NonNull Context context) {
        this(context, null);
    }

    public DispatchLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DispatchLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (getId() == R.id.dl_outer) {
            idStr = "Outer";
        } else if (getId() == R.id.dl_inner) {
            idStr = "Inner";
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, idStr + " DispatchLayout dispatchTouchEvent" + getActionStr(event.getAction()));
        boolean dispatched = super.dispatchTouchEvent(event);
        return dispatched;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.e(TAG, idStr + " DispatchLayout onInterceptTouchEvent" + getActionStr(event.getAction()));
        boolean intercept = true;
        // 如果拦截了ACTION_DOWN事件，则包括ACTION_DOWN以及其后的一系列ACTION_DOWN事件都无法再被分发到其子View。
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            intercept = false;
        }
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, idStr + " DispatchLayout onTouchEvent" + getActionStr(event.getAction()));
        return super.onTouchEvent(event);
    }

}
