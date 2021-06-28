package com.zhpan.sample.jetpack.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils

/**
 * @author zhangpan
 * @date 2021/6/28
 */
class OnceTaskWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        try {
            ToastUtils.showShort("MyWorker is start")
            LogUtils.e("MyWorker is start")
            Thread.sleep(4000)
            LogUtils.e("MyWorker finished")
            ToastUtils.showShort("MyWorker finished")
        } catch (e: InterruptedException) {
            e.printStackTrace()
            return Result.failure()
        }
        return Result.success()
    }
}