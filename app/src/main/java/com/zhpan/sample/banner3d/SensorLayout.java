package com.zhpan.sample.banner3d;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Scroller;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangpan
 * @date 2021/7/29
 */
public class SensorLayout extends FrameLayout implements SensorEventListener {
    private final SensorManager mSensorManager;
    private float[] mAccelerateValues;
    private float[] mMagneticValues;
    private final Scroller mScroller;
    private double mDegreeYMin = -50;
    private double mDegreeYMax = 50;
    private double mDegreeXMin = -50;
    private double mDegreeXMax = 50;
    private boolean hasChangeX;
    private int scrollX;
    private boolean hasChangeY;
    private int scrollY;
    private static final double mXMoveDistance = 20;
    private static final double mYMoveDistance = 0;
    private int mDirection = 1;

    public SensorLayout(@NonNull Context context) {
        this(context, null);
    }

    public SensorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SensorLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        mSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        // 重力传感器
        if (mSensorManager != null) {
            Sensor accelerateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            // 地磁场传感器
            Sensor magneticSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            mSensorManager.registerListener(this, accelerateSensor, SensorManager.SENSOR_DELAY_GAME);
            mSensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_GAME);
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            mAccelerateValues = event.values;
        }
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            mMagneticValues = event.values;
        }
        float[] values = new float[3];
        float[] R = new float[9];
        if (mMagneticValues != null)
            SensorManager.getRotationMatrix(R, null, mAccelerateValues, mMagneticValues);
        SensorManager.getOrientation(R, values);
        // x轴的偏转角度
        values[1] = (float) Math.toDegrees(values[1]);
        // y轴的偏转角度
        values[2] = (float) Math.toDegrees(values[2]);
        double degreeX = values[1];
        double degreeY = values[2];
        if (degreeY <= 0 && degreeY > mDegreeYMin) {
            hasChangeX = true;
            scrollX = (int) (degreeY / Math.abs(mDegreeYMin) * mXMoveDistance * mDirection);
        } else if (degreeY > 0 && degreeY < mDegreeYMax) {
            hasChangeX = true;
            scrollX = (int) (degreeY / Math.abs(mDegreeYMax) * mXMoveDistance * mDirection);
        }
        if (degreeX <= 0 && degreeX > mDegreeXMin) {
            hasChangeY = true;
            scrollY = (int) (degreeX / Math.abs(mDegreeXMin) * mYMoveDistance * mDirection);
        } else if (degreeX > 0 && degreeX < mDegreeXMax) {
            hasChangeY = true;
            scrollY = (int) (degreeX / Math.abs(mDegreeXMax) * mYMoveDistance * mDirection);
        }
        smoothScroll(hasChangeX ? scrollX : mScroller.getFinalX(), hasChangeY ? scrollY : mScroller.getFinalY());
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void smoothScroll(int destX, int destY) {
        int scrollY = getScrollY();
        int delta = destY - scrollY;
        mScroller.startScroll(destX, scrollY, 0, delta, 200);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    public void unregister() {
        mSensorManager.unregisterListener(this);
    }

    public void setDegreeYMin(double degreeYMin) {
        mDegreeYMin = degreeYMin;
    }

    public void setDegreeYMax(double degreeYMax) {
        mDegreeYMax = degreeYMax;
    }

    public void setDegreeXMin(double degreeXMin) {
        mDegreeXMin = degreeXMin;
    }

    public void setDegreeXMax(double degreeXMax) {
        mDegreeXMax = degreeXMax;
    }

    public void setDirection(@ADirection int direction) {
        mDirection = direction;
    }

    @IntDef({DIRECTION_LEFT, DIRECTION_RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    public @interface ADirection {

    }

    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_RIGHT = -1;
}
