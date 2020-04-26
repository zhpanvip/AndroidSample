package com.zhpan.sample.dispatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.zhpan.sample.R;

public class DispatchActivity extends AppCompatActivity {

    public static final String TAG = "DispatchEvent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
//        findViewById(R.id.dispatch_view).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(TAG, "DispatchView clicked");
//            }
//        });

//        findViewById(R.id.dl_inner).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(TAG, "DispatchLayout clicked");
//            }
//        });

//        findViewById(R.id.dl_inner).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.e(TAG, "DispatchLayout setOnTouchListener");
//                return false;
//            }
//        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "Activity dispatchTouchEvent" + getActionStr(event.getAction()));
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "Activity onTouchEvent" + getActionStr(event.getAction()));
        return super.onTouchEvent(event);
    }

    public static String getActionStr(int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return ",Action Down";
            case MotionEvent.ACTION_MOVE:
                return ",Action Move";
            case MotionEvent.ACTION_UP:
                return ",Action Up";
        }
        return "";
    }
}
