package com.zhpan.sample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import com.zhpan.sample.animation.AnimateEntryActivity
import com.zhpan.sample.lifecycle.LifecycleActivity
import com.zhpan.sample.viewpager2.activity.*

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
            R.id.item_view_pager -> ViewPager2EntryActivity::class.java
            R.id.item_animation -> AnimateEntryActivity::class.java
            R.id.item_lifecycle -> LifecycleActivity::class.java
            else -> ViewPager2EntryActivity::class.java
        }
    }
}
