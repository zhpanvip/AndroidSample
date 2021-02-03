package com.zhpan.sample.behavior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhpan.sample.R;

public class BehaviorEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_enter);
    }

    public void onClick(View view) {
        Intent intent = null;
        if (view.getId() == R.id.btn_toolbar_behavior) {
            intent = new Intent(this, ToolbarBehaviorActivity.class);
        } else if (view.getId() == R.id.btn_header_behavior) {
            intent = new Intent(this, HeaderBehaviorActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}