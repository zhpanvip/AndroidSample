package com.zhpan.sample.jetpack.workmanager

import android.os.Bundle
import com.zhpan.library.BaseViewBindingActivity
import com.zhpan.sample.R
import com.zhpan.sample.databinding.ActivityWorkManagerBinding

class WorkManagerActivity : BaseViewBindingActivity<ActivityWorkManagerBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
    }

    override fun createViewBinding(): ActivityWorkManagerBinding {
        return ActivityWorkManagerBinding.inflate(layoutInflater)
    }
}