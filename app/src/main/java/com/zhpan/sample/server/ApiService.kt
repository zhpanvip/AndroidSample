package com.zhpan.sample.server

import com.blankj.utilcode.util.Utils
import com.zhpan.sample.model.ArticleResponse
import com.zhpan.sample.model.response.BaseResponse
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.File

/**
 * @author zhangpan
 * @date 2021/3/23
 */
interface ApiService {
    @GET("article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): BaseResponse<ArticleResponse>

    companion object {
        private const val BASE_URL = "https://www.wanandroid.com/"

        private val okHttpClientBuilder: OkHttpClient.Builder
            get() {
                val cacheFile =
                        File(Utils.getApp().cacheDir, "cache")
                val cache = Cache(cacheFile, 1024 * 1024 * 100) //100Mb
                return OkHttpClient.Builder()
                        .addInterceptor(LoggingInterceptor())
                        .cache(cache)
            }

        fun create(): ApiService {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
        }
    }

}