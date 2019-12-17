package com.zhpan.viewpagersample.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.zhpan.viewpagersample.R
import com.zhpan.viewpagersample.ScaleInTransformer
import com.zhpan.viewpagersample.adapter.MyAdapter
import kotlinx.android.synthetic.main.activity_main.*

import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        startActivity(Intent(this, when (view.id) {
            R.id.btn_pager_fragment -> FragmentActivity::class.java
            else -> PagerActivity::class.java
        }))
    }
}
