package com.zhpan.sample

import com.zhpan.library.BaseViewBindingActivity
import com.zhpan.sample.databinding.ActivityMainBinding

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {

    override fun createViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}
