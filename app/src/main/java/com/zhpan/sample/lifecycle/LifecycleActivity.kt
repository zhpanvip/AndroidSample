package com.zhpan.sample.lifecycle

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import com.zhpan.sample.R

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        val lifecycleView=findViewById<LifecycleView>(R.id.lifecycle_view)
        lifecycle.addObserver(lifecycleView)

    }
}
