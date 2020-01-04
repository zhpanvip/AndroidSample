package com.zhpan.sample.animation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ScreenUtils;
import com.zhpan.sample.R;
import com.zhpan.sample.common.CircleCenter;
import com.zhpan.sample.common.CircleCenterEvaluator;
import com.zhpan.sample.common.CircleView;


public class ValueAnimatorActivity extends AppCompatActivity {
    private CircleView mCircleView;
    private ValueAnimator valueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);
        mCircleView = findViewById(R.id.circleView);
    }

    public void startAnim(View view) {
        switch (view.getId()) {
            case R.id.button:
                ofArgb();
                break;
            case R.id.button2:
                ofObject();
                break;
        }
    }

    private void ofArgb() {
        valueAnimator = ValueAnimator.ofArgb(0xffff0000, 0xff00ff00);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                mCircleView.setCirCleColor(color);
            }
        });
        valueAnimator.start();
    }

    private void ofObject() {
        CircleCenter startValue = new CircleCenter(0, 150);
        CircleCenter endValue = new CircleCenter(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight());
        valueAnimator = ValueAnimator.ofObject(new CircleCenterEvaluator(), startValue, endValue);
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setEvaluator(new CircleCenterEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                CircleCenter circleCenter = (CircleCenter) animation.getAnimatedValue();
                mCircleView.setX(circleCenter.getCenterX());
                mCircleView.setY(circleCenter.getCenterY());
                mCircleView.invalidate();
            }
        });
        valueAnimator.start();

    }


}
