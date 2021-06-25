package com.zhpan.sample.nestedscroll.toolbar

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhpan.library.recyclerview.ui.CustomRecyclerView
import com.zhpan.sample.R
import com.zhpan.sample.adapter.DataAdapter

class ToolbarBehaviorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behavior)
        val recyclerView=findViewById<CustomRecyclerView>(R.id.recyclerView)
        val toolbar=findViewById<BehaviorToolbar>(R.id.toolbar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val headerView = LayoutInflater.from(this).inflate(R.layout.layout_behavior_header, recyclerView, false)
        recyclerView.addHeadView(headerView)
        recyclerView.addItemDecoration(DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL))
        recyclerView.adapter = DataAdapter()
        toolbar.setTitle(getString(R.string.behavior))
        toolbar.setupWithHeader(headerView)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}