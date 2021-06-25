package com.zhpan.sample.animation.viewanim;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.zhpan.sample.R;


public class ViewAnimationActivity extends AppCompatActivity {

    private ImageView mImageView;
    private float pivotX;
    private float pivotY;
    private String tag = "AnimateEntryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        mImageView = findViewById(R.id.iv_icon);
    }

    public void onClick(View view) {
        pivotX = (mImageView.getRight() - mImageView.getLeft()) / 2f;
        pivotY = (mImageView.getBottom() - mImageView.getTop()) / 2f;
        switch (view.getId()) {
            case R.id.btn_alpha:
                mImageView.startAnimation(getAlphaAnimation());
                break;
            case R.id.btn_rotate:
                mImageView.startAnimation(getRotateAnimation(1));
                break;
            case R.id.btn_scale:
                mImageView.startAnimation(getScaleAnimation());
                break;
            case R.id.btn_translate:
                mImageView.startAnimation(getTranslateAnimation());
                break;

            case R.id.btn_set:
                mImageView.startAnimation(getAnimationSet());
                break;
        }
    }
    private AnimationSet getAnimationSet() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(2000);
        animationSet.addAnimation(getRotateAnimation(0));
        animationSet.addAnimation(getScaleAnimation());
        animationSet.addAnimation(getAlphaAnimation());
        animationSet.addAnimation(getTranslateAnimation());
        return animationSet;
    }

    private TranslateAnimation getTranslateAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(-200, 0, -100, 0);
        translateAnimation.setDuration(2000);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.e(tag, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e(tag, "onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.e(tag, "onAnimationRepeat");
            }
        });
        return translateAnimation;
    }

    private ScaleAnimation getScaleAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.2f, 1, 0.2f, 1, pivotX, pivotY);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(2000);
        return scaleAnimation;
    }

    private RotateAnimation getRotateAnimation(int repeatCount) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, pivotX, pivotY);
        rotateAnimation.setDuration(2000);
//        rotateAnimation.setInterpolator(new BounceInterpolator());
//        rotateAnimation.setFillAfter(true);
//        rotateAnimation.setRepeatCount(1);
        return rotateAnimation;
    }

    private AlphaAnimation getAlphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        return alphaAnimation;
    }
}
