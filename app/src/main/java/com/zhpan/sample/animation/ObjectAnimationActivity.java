package com.zhpan.sample.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.zhpan.sample.R;
import com.zhpan.library.view.CircleView;


public class ObjectAnimationActivity extends AppCompatActivity {
    private CircleView mCircleView;
    private ImageView mImageView;
    private AnimatorSet animatorSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);
        mCircleView = findViewById(R.id.circle_view);
        mImageView = findViewById(R.id.iv_icon);
        mCircleView.startAnimate();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_alpha:
                alphaAnimator();
                break;
            case R.id.btn_rotate:
                rotateAnimator();
                break;
            case R.id.btn_translation:
                transitionAnimator();
                break;
            case R.id.btn_scale:
                scaleAnimator();
                break;
            case R.id.btn_set:
                animSet();
                break;
            case R.id.btn_circle_scale:
                startScale();
                break;
        }
//        if (mObjectAnimator != null) {
//            mObjectAnimator.setDuration(1000);
//            mObjectAnimator.start();
//        }
    }

    private void animSet() {
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImageView, "scaleY", 1f, 2f, 1f);
//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImageView, "rotation", 0f, 360f);
//        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mImageView, "alpha", 1f, 0f, 1f);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.setDuration(2000);
//        animatorSet.playSequentially(animator1,animator2,animator3);
////        animatorSet.playTogether(animator1, animator2, animator3);
//        animatorSet.start();

        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
        PropertyValuesHolder rotation = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 2f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(mImageView, alpha, rotation, scaleY).setDuration(2000).start();
    }

    private void scaleAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "scaleY", 1f, 2f, 1f);
        animator.setDuration(2000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        animator.start();
    }

    private void transitionAnimator() {
        float translationY = mImageView.getTranslationY();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "translationY", translationY, translationY + 400);
        animator.setDuration(2000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();

    }

    private void rotateAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "rotation", 0f, 360f);
        animator.setDuration(2000);
        if (!animator.isRunning())
            animator.start();
    }

    private void alphaAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "alpha", 1f, 0f, 1f);
        animator.setDuration(2000);
        animator.start();
    }

    private void startScale() {
        if (animatorSet == null) {
            animatorSet = new AnimatorSet();
            animatorSet.setDuration(1000);
            animatorSet.playTogether(ObjectAnimator.ofFloat(mCircleView, "ScaleX", 1.2f),
                    ObjectAnimator.ofFloat(mCircleView, "ScaleY", 1.2f));
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    AnimatorSet animatorSet1 = new AnimatorSet();
                    animatorSet1.playTogether(ObjectAnimator.ofFloat(mCircleView, "ScaleX", 0.8f),
                            ObjectAnimator.ofFloat(mCircleView, "ScaleY", 0.8f));
                    animatorSet1.setDuration(1000);
                    animatorSet1.start();
                }
            });
        }
        if (!animatorSet.isRunning()) {
            animatorSet.start();
        }
    }
}
