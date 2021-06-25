package com.zhpan.sample.nestedscroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhpan.sample.R;
import com.zhpan.sample.material.behavior.HeaderBehaviorActivity;
import com.zhpan.sample.nestedscroll.demo1.NestedScrollActivity;
import com.zhpan.sample.nestedscroll.demo2.NestedScrollActivity2;
import com.zhpan.sample.nestedscroll.toolbar.ToolbarBehaviorActivity;

public class NestedEntryActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_behavior_enter);
  }

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
}