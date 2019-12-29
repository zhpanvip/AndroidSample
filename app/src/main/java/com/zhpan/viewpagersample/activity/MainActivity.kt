package com.zhpan.viewpagersample.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import com.zhpan.viewpagersample.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        startActivity(Intent(this, getIntentActivity(view)))
    }

    private fun getIntentActivity(view: View): Class<out Activity>? {
        return when (view.id) {
            R.id.btn_pager_fragment -> VP2FragmentActivity::class.java
            R.id.btn_view_pager -> VPFragmentActivity::class.java
            R.id.btn_fragment_lifecycle -> FragmentLifecycleActivity::class.java
            else -> VP2ViewsActivity::class.java
        }
    }
}
