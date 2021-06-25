package com.zhpan.sample.view.viewpager2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.viewpager.widget.ViewPager
import com.zhpan.sample.R
import com.zhpan.sample.view.viewpager2.adapter.VPFragmentAdapter

class VPFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = VPFragmentAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
    }
}
