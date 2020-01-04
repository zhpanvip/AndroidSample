package com.zhpan.sample.viewpager2.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zhpan.sample.R

class ViewPager2EntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vp_entry)
    }

    fun onClick(view: View) {
        startActivity(Intent(this, getIntentActivity(view)))
    }

    private fun getIntentActivity(view: View): Class<out Activity>? {
        return when (view.id) {
            R.id.btn_view_pager2_fragment -> VP2FragmentActivity::class.java
            R.id.btn_view_pager_fragment -> VPFragmentActivity::class.java
            R.id.btn_fragment_lifecycle -> FragmentLifecycleActivity::class.java
            else -> VP2ViewsActivity::class.java
        }
    }
}
