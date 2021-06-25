package com.zhpan.sample.nestedscroll.demo2

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhpan.library.BaseViewBindingActivity
import com.zhpan.sample.databinding.ActivityNestedScroll2Binding
import com.zhpan.sample.adapter.DataAdapter
import com.zhpan.sample.adapter.ImageAdapter
import com.zhpan.sample.nestedscroll.demo2.ext.dp
import com.zhpan.sample.nestedscroll.demo2.view.BehavioralScrollListener
import com.zhpan.sample.nestedscroll.demo2.view.BehavioralScrollView
import com.zhpan.sample.nestedscroll.demo2.view.BottomSheetLayout.Companion.POSITION_MIN

class NestedScrollActivity2 : BaseViewBindingActivity<ActivityNestedScroll2Binding>() {

    private val floatingHeight = 100.dp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvLinkageTop.apply {
            layoutManager = LinearLayoutManager(this@NestedScrollActivity2)
            adapter = ImageAdapter()
        }

        binding.rvLinkageBottom.apply {
            layoutManager = LinearLayoutManager(this@NestedScrollActivity2)
            adapter = DataAdapter()
        }

        binding.linkageScroll.topScrollTarget = { binding.rvLinkageTop }
        binding.linkageScroll.listeners.add(object : BehavioralScrollListener {
            override fun onScrollChanged(v: BehavioralScrollView, from: Int, to: Int) {
                super.onScrollChanged(v, from, to)
                updateFloatState()
            }
        })

        binding.bottomSheet.setup(POSITION_MIN, floatingHeight)
        updateFloatState()
    }

    private fun updateFloatState() {
        if (binding.bottomSheet.indexOfChild(binding.rvLinkageBottom) >= 0) {
            val scrollY = binding.linkageScroll.scrollY
            if (scrollY >= floatingHeight) {
                binding.bottomSheet.visibility = View.GONE
                binding.bottomSheet.removeView(binding.rvLinkageBottom)
                if (binding.layoutBottom.indexOfChild(binding.rvLinkageBottom) < 0) {
                    binding.layoutBottom.addView(binding.rvLinkageBottom)
                }
                binding.linkageScroll.bottomScrollTarget = { binding.rvLinkageBottom }
            }
        } else {
            if (binding.linkageScroll.scrollY < floatingHeight) {
                binding.linkageScroll.bottomScrollTarget = null
                if (binding.layoutBottom.indexOfChild(binding.rvLinkageBottom) >= 0) {
                    binding.layoutBottom.removeView(binding.rvLinkageBottom)
                }
                if (binding.bottomSheet.indexOfChild(binding.rvLinkageBottom) < 0) {
                    binding.bottomSheet.addView(binding.rvLinkageBottom)
                }
                binding.bottomSheet.visibility = View.VISIBLE
            }
        }
    }

    override fun createViewBinding(): ActivityNestedScroll2Binding {
        return ActivityNestedScroll2Binding.inflate(layoutInflater)
    }
}