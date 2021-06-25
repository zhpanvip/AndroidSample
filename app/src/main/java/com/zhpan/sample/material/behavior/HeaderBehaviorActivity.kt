package com.zhpan.sample.material.behavior

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.zhpan.sample.R
import com.zhpan.sample.adapter.DataAdapter

class HeaderBehaviorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_scale)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.addItemDecoration(DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL))
        recyclerView.adapter = DataAdapter()
    }
}