package com.zhpan.sample

import android.app.Activity
import android.content.Intent

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.zhpan.library.BaseViewBindingActivity
import com.zhpan.sample.animation.AnimateEntryActivity
import com.zhpan.sample.databinding.ActivityMainBinding
import com.zhpan.sample.jetpack.JetpackEntryActivity
import com.zhpan.sample.nestedscroll.NestedEntryActivity
import com.zhpan.sample.view.dispatch.DispatchActivity
import com.zhpan.sample.view.viewpager2.activity.*

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>(), OnClickListener {
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding.itemViewPager.setOnClickListener(this)
        binding.itemAnimation.setOnClickListener(this)
        binding.itemJectpack.setOnClickListener(this)
        binding.itemNestedScroll.setOnClickListener(this)
        binding.itemDispatch.setOnClickListener(this)
    }

    private fun getIntentActivity(view: View?): Class<out Activity> {
        return when (view?.id) {
            R.id.item_view_pager -> ViewPager2EntryActivity::class.java
            R.id.item_animation -> AnimateEntryActivity::class.java
            R.id.item_lifecycle -> JetpackEntryActivity::class.java
            R.id.item_nested_scroll -> NestedEntryActivity::class.java
            R.id.item_dispatch -> DispatchActivity::class.java
            else -> ViewPager2EntryActivity::class.java
        }
    }

    override fun createViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onClick(view: View?) {
        startActivity(Intent(this, getIntentActivity(view)))
    }
}
