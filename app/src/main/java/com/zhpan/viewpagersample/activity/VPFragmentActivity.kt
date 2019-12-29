package com.zhpan.viewpagersample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhpan.viewpagersample.R
import com.zhpan.viewpagersample.adapter.VPFragmentAdapter
import kotlinx.android.synthetic.main.activity_view_pager.*

class VPFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        view_pager.adapter = VPFragmentAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
    }
}
