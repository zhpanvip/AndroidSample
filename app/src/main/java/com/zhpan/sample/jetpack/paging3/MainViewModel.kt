package com.zhpan.sample.jetpack.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zhpan.library.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * @author zhangpan
 * @date 2021/3/23
 */
internal class MainViewModel : ViewModel() {
    fun fetchPagingData(): Flow<PagingData<Article>> {
        return Repository.fetchPagingData().cachedIn(viewModelScope)
    }
}