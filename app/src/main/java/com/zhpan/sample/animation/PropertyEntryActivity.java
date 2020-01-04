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
        switch (view.getId()) {
            case R.id.btn_value_animation:
                intent = new Intent(this, ValueAnimatorActivity.class);
                break;
            case R.id.btn_object_animation:
                intent = new Intent(this, ObjectAnimationActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
