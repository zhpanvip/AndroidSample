package com.zhpan.sample.viewpager2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import com.zhpan.sample.R
import com.zhpan.sample.viewpager2.adapter.VPFragmentAdapter
import kotlinx.android.synthetic.main.activity_view_pager.*

class VPFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        view_pager.adapter = VPFragmentAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
    }
}
