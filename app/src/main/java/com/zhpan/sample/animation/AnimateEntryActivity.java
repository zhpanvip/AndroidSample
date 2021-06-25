package com.zhpan.sample.animation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhpan.library.BaseViewBindingActivity;
import com.zhpan.sample.R;
import com.zhpan.sample.animation.propertyanim.InterpolatorActivity;
import com.zhpan.sample.animation.propertyanim.PropertyEntryActivity;
import com.zhpan.sample.animation.viewanim.ViewAnimationActivity;
import com.zhpan.sample.databinding.ActivityAnimateEntryBinding;
import org.jetbrains.annotations.NotNull;

public class AnimateEntryActivity extends BaseViewBindingActivity<ActivityAnimateEntryBinding>
    implements
    View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding.btnInterpolator.setOnClickListener(this);
    binding.btnPropertyAnimation.setOnClickListener(this);
    binding.btnInterpolator.setOnClickListener(this);
  }

  public void onClick(View view) {
    Intent intent = null;
    if (view.getId() == R.id.btn_view_animation) {
      intent = new Intent(this, ViewAnimationActivity.class);
    } else if (view.getId() == R.id.btn_property_animation) {
      intent = new Intent(this, PropertyEntryActivity.class);
    } else if (view.getId() == R.id.btn_interpolator) {
      intent = new Intent(this, InterpolatorActivity.class);
    }
    if (null != intent) {
      startActivity(intent);
    }
  }

  @NotNull @Override protected ActivityAnimateEntryBinding createViewBinding() {
    return ActivityAnimateEntryBinding.inflate(getLayoutInflater());
  }
}
