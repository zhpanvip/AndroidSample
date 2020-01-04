package com.zhpan.sample.common;

import android.animation.TypeEvaluator;

public class CircleCenterEvaluator implements TypeEvaluator<CircleCenter> {
    @Override
    public CircleCenter evaluate(float fraction, CircleCenter startValue, CircleCenter endValue) {
        // x方向匀速移动
        float centerX = startValue.getCenterX() + fraction * (endValue.getCenterX() - startValue.getCenterX());
        // y方向抛物线加速移动
        float centerY = startValue.getCenterY() + fraction * fraction * (endValue.getCenterY() - startValue.getCenterY());
        return new CircleCenter(centerX, centerY);
    }
}
