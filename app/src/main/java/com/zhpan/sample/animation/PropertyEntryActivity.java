package com.zhpan.sample.animation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zhpan.sample.R;


public class PropertyEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_entry);
    }

    public void onClick(View view) {
        Intent intent = null;
        if (view.getId() == R.id.btn_value_animation) {
            intent = new Intent(this, ValueAnimatorActivity.class);
        } else if (view.getId() == R.id.btn_value_animation) {
            intent = new Intent(this, ObjectAnimationActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
