package com.zhpan.sample.behavior

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhpan.sample.R
import kotlinx.android.synthetic.main.activity_behavior.*

class BehaviorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behavior)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val headerView = LayoutInflater.from(this).inflate(R.layout.layout_behavior_header, recyclerView, false)
        recyclerView.addHeadView(headerView)
        recyclerView.addItemDecoration(DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL))
        recyclerView.adapter = BehaviorAdapter()
        toolbar.setTitle(getString(R.string.behavior))
        toolbar.setupWithHeader(headerView)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}