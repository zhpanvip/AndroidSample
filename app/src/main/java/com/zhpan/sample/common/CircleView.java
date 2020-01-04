package com.zhpan.sample.common;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zhpan on 2019/07/20.
 */

public class CircleView extends View {
    private Paint mPaint;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#FF0000"));
        mPaint.setAntiAlias(true);
    }

    public void setCirCleColor(int color) {
        mPaint.setColor(color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        float radius = Math.min(width, height) / 2f;
        canvas.drawCircle(width / 2f, height / 2f, radius, mPaint);
    }

    public void setProgress(float progress) {
        Log.e("CircleView", "progress---->" + progress);
    }

    public void startAnimate() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "progress", 0, 1f);
        objectAnimator.setDuration(200);
        objectAnimator.start();
    }
}
