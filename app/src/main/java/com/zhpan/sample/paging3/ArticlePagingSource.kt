package com.zhpan.sample.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zhpan.sample.model.Article
import com.zhpan.sample.server.ApiService
import java.lang.Exception

/**
 *
 * @author zhangpan
 * @date 2021/3/23
 */
class ArticlePagingSource(private val apiService: ApiService) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 0
            val articleResponse = apiService.getHomeArticles(page)
            val articleList = articleResponse.data?.datas
            val preKey = if (page > 0) page - 1 else null
            val nextKey = if (articleList!!.isEmpty()) null else page + 1
            LoadResult.Page(articleList, preKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = null
}