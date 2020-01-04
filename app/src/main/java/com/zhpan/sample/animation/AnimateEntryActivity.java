package com.zhpan.sample.animation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zhpan.sample.R;


public class AnimateEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate_entry);
    }

    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_view_animation:
                intent = new Intent(this, ViewAnimationActivity.class);
                break;
            case R.id.btn_property_animation:
                intent = new Intent(this, PropertyEntryActivity.class);
                break;
            case R.id.btn_interpolator:
                intent = new Intent(this, InterpolatorActivity.class);
                break;
        }
        if (null != intent)
            startActivity(intent);
    }
}
