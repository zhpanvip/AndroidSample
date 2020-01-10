package com.zhpan.sample.lifecycle

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import com.zhpan.sample.R
import kotlinx.android.synthetic.main.activity_lifecycle.*

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        lifecycle.addObserver(lifecycle_view)
    }
}
