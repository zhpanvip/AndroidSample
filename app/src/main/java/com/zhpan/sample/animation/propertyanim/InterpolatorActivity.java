package com.zhpan.sample.animation.propertyanim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.zhpan.sample.R;


public class InterpolatorActivity extends AppCompatActivity {
    private View mCircleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
        mCircleView = findViewById(R.id.circle_view);
    }

    public void onClick(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mCircleView,"ScaleX", 0f,1f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(mCircleView,"ScaleY", 0f,1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);

        switch (view.getId()) {
            case R.id.btn_accelerate:

                objectAnimator.setInterpolator(new AccelerateInterpolator(2));
                objectAnimator2.setInterpolator(new AccelerateInterpolator(2));
                break;
            case R.id.btn_decelerate:
                objectAnimator.setInterpolator(new DecelerateInterpolator());
                objectAnimator2.setInterpolator(new DecelerateInterpolator());
                break;
            case R.id.btn_bounce:
                objectAnimator.setInterpolator(new BounceInterpolator());
                objectAnimator2.setInterpolator(new BounceInterpolator());
                break;
            case R.id.btn_anticipate:
                objectAnimator.setInterpolator(new AnticipateInterpolator());
                objectAnimator2.setInterpolator(new AnticipateInterpolator());
                break;
            case R.id.btn_overshoot:
                objectAnimator.setInterpolator(new OvershootInterpolator());
                objectAnimator2.setInterpolator(new OvershootInterpolator());
                    break;
        }
        animatorSet.playTogether(objectAnimator,objectAnimator2);
        animatorSet.start();
    }
}
