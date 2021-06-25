package com.zhpan.sample.nestedscroll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhpan.library.BaseViewBindingActivity;
import com.zhpan.sample.R;
import com.zhpan.sample.databinding.ActivityNestedEnterBinding;
import com.zhpan.sample.nestedscroll.demo1.NestedScrollActivity;
import com.zhpan.sample.nestedscroll.demo2.NestedScrollActivity2;
import com.zhpan.sample.nestedscroll.toolbar.ToolbarBehaviorActivity;
import org.jetbrains.annotations.NotNull;

public class NestedEntryActivity extends BaseViewBindingActivity<ActivityNestedEnterBinding>
    implements
    View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding.btnToolbarBehavior.setOnClickListener(this);
    binding.nestedScroll1.setOnClickListener(this);
    binding.nestedScroll2.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    Intent intent = null;
    if (view.getId() == R.id.btn_toolbar_behavior) {
      intent = new Intent(this, ToolbarBehaviorActivity.class);
    } else if (view.getId() == R.id.nested_scroll1) {
      intent = new Intent(this, NestedScrollActivity.class);
    } else if (view.getId() == R.id.nested_scroll2) {
      intent = new Intent(this, NestedScrollActivity2.class);
    }
    if (intent != null) {
      startActivity(intent);
    }
  }

  @NotNull @Override protected ActivityNestedEnterBinding createViewBinding() {
    return ActivityNestedEnterBinding.inflate(getLayoutInflater());
  }
}