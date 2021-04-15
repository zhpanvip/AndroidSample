package com.zhpan.sample.nestedscroll;

import android.content.Context;
import android.view.ViewConfiguration;

public class FlingHelper {
    private static final float DECELERATION_RATE = ((float) (Math.log(0.78d) / Math.log(0.9d)));
    private static final float mFlingFriction = ViewConfiguration.getScrollFriction();
    private static float mPhysicalCoeff;

    public FlingHelper(Context context) {
        mPhysicalCoeff = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
    }

    private double getSplineDeceleration(int i) {
        return Math.log((double) ((0.35f * ((float) Math.abs(i))) / (mFlingFriction * mPhysicalCoeff)));
    }

    private double getSplineDecelerationByDistance(double d) {
        return ((((double) DECELERATION_RATE) - 1.0d) * Math.log(d / ((double) (mFlingFriction * mPhysicalCoeff)))) / ((double) DECELERATION_RATE);
    }

    /**
     * 速度转距离
     *
     * @param velocityY filing 速度
     * @return 可以filing的距离
     */
    public double getSplineFlingDistance(int velocityY) {
        return Math.exp(getSplineDeceleration(velocityY) * (((double) DECELERATION_RATE) / (((double) DECELERATION_RATE) - 1.0d))) * ((double) (mFlingFriction * mPhysicalCoeff));
    }

    public int getVelocityByDistance(double d) {
        return Math.abs((int) (((Math.exp(getSplineDecelerationByDistance(d)) * ((double) mFlingFriction)) * ((double) mPhysicalCoeff)) / 0.3499999940395355d));
    }
}
