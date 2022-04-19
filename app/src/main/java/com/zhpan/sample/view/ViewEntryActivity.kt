package com.zhpan.sample.view

import android.content.Intent
import android.os.Bundle
import com.zhpan.library.BaseViewBindingActivity
import com.zhpan.sample.banner3d.Banner3dActivity
import com.zhpan.sample.databinding.ActivityViewEntryBinding
import com.zhpan.sample.view.dispatch.DispatchActivity
import com.zhpan.sample.view.viewpager2.activity.ViewPager2EntryActivity

class ViewEntryActivity : BaseViewBindingActivity<ActivityViewEntryBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewpager2.setOnClickListener {
            startActivity(Intent(this, ViewPager2EntryActivity::class.java))
        }
        binding.dispatchView.setOnClickListener {
            startActivity(Intent(this, DispatchActivity::class.java))
        }

        binding.itemBanner.setOnClickListener {
            startActivity(Intent(this, Banner3dActivity::class.java))
        }
    }

    override fun createViewBinding(): ActivityViewEntryBinding {
        return ActivityViewEntryBinding.inflate(layoutInflater)
    }
}