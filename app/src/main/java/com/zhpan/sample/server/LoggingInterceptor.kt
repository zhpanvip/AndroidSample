package com.zhpan.sample.server

import android.util.Log
import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by zhpan on 2020/7/8.
 * Description:
 */
class LoggingInterceptor : Interceptor {
    companion object {
        val TAG: String = "LOG"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        val request = chain.request()
        val t1 = System.nanoTime() //请求发起的时间
        Log.e(
            TAG, String.format(
                "请求URL------%s on %s%n请求头------%s",
                request.url(), chain.connection(), request.headers()
            )
        )

        val response = chain.proceed(request)
        val t2 = System.nanoTime() //收到响应的时间

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        val responseBody = response.peekBody(1024 * 1024.toLong())
        Log.e(
            TAG, String.format(
                "响应URL-------: %s %n响应数据------%s 请求用时--------%.1fms%n%s",
                response.request().url(),
                responseBody.string(),
                (t2 - t1) / 1e6,
                response.headers()
            )
        )
        return response
    }
}