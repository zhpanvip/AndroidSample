package com.zhpan.sample.viewpager2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import com.zhpan.sample.R
import com.zhpan.sample.viewpager2.ScaleInTransformer
import com.zhpan.sample.viewpager2.adapter.ViewPager2Adapter
import kotlinx.android.synthetic.main.activity_pager.*
import java.util.ArrayList

class VP2ViewsActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var myAdapter: ViewPager2Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)
        viewPager2 = findViewById(R.id.view_pager2)
        myAdapter = ViewPager2Adapter()
        myAdapter.setList(data)
        viewPager2.adapter = myAdapter
        viewPager2.apply {
            val recyclerView = getChildAt(0) as RecyclerView
            recyclerView.apply {
                val padding = resources.getDimensionPixelOffset(R.dimen.dp_10) +
                        resources.getDimensionPixelOffset(R.dimen.dp_10)
                // setting padding on inner RecyclerView puts overscroll effect in the right place
                setPadding(padding, 0, padding, 0)
                clipToPadding = false
            }
        }
        viewPager2.offscreenPageLimit = 1
//        val compositePageTransformer = CompositePageTransformer()
//        compositePageTransformer.addTransformer(ScaleInTransformer())
//        compositePageTransformer.addTransformer(MarginPageTransformer(resources.getDimension(R.dimen.dp_10).toInt()))
        viewPager2.setPageTransformer(MarginPageTransformer(resources.getDimension(R.dimen.dp_10).toInt()))
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Toast.makeText(this@VP2ViewsActivity, "page selected $position", Toast.LENGTH_SHORT).show()
            }
        })
        indicatorView
                .setSliderColor(getResColor(R.color.red_normal_color), getResColor(R.color.red_checked_color))
                .setSliderWidth(resources.getDimension(R.dimen.dp_17))
                .setSliderHeight(resources.getDimension(R.dimen.dp_5))
                .setSlideMode(IndicatorSlideMode.WORM)
                .setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                .setupWithViewPager(viewPager2)
//        viewPager2.isUserInputEnabled = false
//        viewPager2.orientation=ViewPager2.ORIENTATION_VERTICAL

    }

    protected fun getResColor(@ColorRes color: Int): Int {
        return resources.getColor(color)
    }

    private val data: ArrayList<Int>
        get() {
            val list = ArrayList<Int>()
            for (i in 0..3) {
                list.add(i)
            }
            return list
        }

    fun fakeDragBy(view: View) {
        myAdapter.getData().clear()
        myAdapter.notifyDataSetChanged()
        myAdapter.setList(data)
        myAdapter.notifyDataSetChanged()
//        viewPager2.beginFakeDrag()
//        if (viewPager2.fakeDragBy(-310f))
//            viewPager2.endFakeDrag()
    }
}
