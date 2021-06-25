package com.zhpan.sample.animation.propertyanim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhpan.library.BaseViewBindingActivity;
import com.zhpan.sample.R;
import com.zhpan.sample.databinding.ActivityPropertyEntryBinding;
import org.jetbrains.annotations.NotNull;

public class PropertyEntryActivity extends BaseViewBindingActivity<ActivityPropertyEntryBinding> implements
    View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.btnObjectAnimation.setOnClickListener(this);
        binding.btnValueAnimation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        if (view.getId() == R.id.btn_value_animation) {
            intent = new Intent(this, ValueAnimatorActivity.class);
        } else if (view.getId() == R.id.btn_object_animation) {
            intent = new Intent(this, ObjectAnimationActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    @NotNull @Override protected ActivityPropertyEntryBinding createViewBinding() {
        return ActivityPropertyEntryBinding.inflate(getLayoutInflater());
    }
}
