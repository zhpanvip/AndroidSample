package com.zhpan.sample.jetpack

import android.content.Intent
import android.os.Bundle
import com.zhpan.library.BaseViewBindingActivity
import com.zhpan.sample.databinding.ActivityJetpackEntryBinding
import com.zhpan.sample.jetpack.lifecycle.LifecycleActivity
import com.zhpan.sample.jetpack.paging3.PagingActivity
import com.zhpan.sample.jetpack.workmanager.WorkManagerActivity

class JetpackEntryActivity : BaseViewBindingActivity<ActivityJetpackEntryBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.itemLifecycle.setOnClickListener {
            startActivity(Intent(this, LifecycleActivity::class.java))
        }
        binding.itemPaging.setOnClickListener {
            startActivity(Intent(this, PagingActivity::class.java))
        }
        binding.itemWorkManager.setOnClickListener {
            startActivity(Intent(this, WorkManagerActivity::class.java))
        }
    }

    override fun createViewBinding(): ActivityJetpackEntryBinding {
        return ActivityJetpackEntryBinding.inflate(layoutInflater)
    }
}