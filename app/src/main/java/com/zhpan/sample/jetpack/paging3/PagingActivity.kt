package com.zhpan.sample.jetpack.paging3

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhpan.sample.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PagingActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private val articleAdapter = ArticleAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        title = "Paging"
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.addItemDecoration(DividerItemDecoration(this@PagingActivity, DividerItemDecoration.VERTICAL))
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = articleAdapter
        lifecycleScope.launch {
            viewModel.fetchPagingData().collect { pagingData ->
                articleAdapter.submitData(pagingData)
            }
        }
        articleAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    progressBar.visibility = View.INVISIBLE
                    recyclerView.visibility = View.VISIBLE
                }
                is LoadState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, "Load Error: ${state.error.message}", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}