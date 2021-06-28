package com.zhpan.sample.jetpack.workmanager

import android.os.Bundle
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequest.Builder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.blankj.utilcode.util.LogUtils
import com.zhpan.library.BaseViewBindingActivity
import com.zhpan.sample.databinding.ActivityWorkManagerBinding
import java.util.concurrent.TimeUnit

class WorkManagerActivity : BaseViewBindingActivity<ActivityWorkManagerBinding>() {

    companion object {
        const val PERIOD_TAG = "period_task";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 执行单次任务
        binding.btnUsage.setOnClickListener {
            val oneTimeWorkRequest = Builder(OnceTaskWorker::class.java).build()
            WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)
        }

        // 执行周期任务
        binding.btnPeriod.setOnClickListener {
            //  制定一个每隔15分钟执行一次的Request
            val periodicWorkRequest =
                PeriodicWorkRequest.Builder(OnceTaskWorker::class.java, 15, TimeUnit.MINUTES)
                    .addTag(PERIOD_TAG)
                    .build()
            // 监听状态
            WorkManager.getInstance(this).getWorkInfoByIdLiveData(periodicWorkRequest.id)
                .observe(this, { workInfo ->
                    LogUtils.d("状态：" + workInfo?.state?.name) // 这里只会是状态只会是ENQUEUE，因为是周期任务。
                    if (workInfo?.state?.isFinished == true) {
                        LogUtils.d("状态：isFinished=true 后台任务已经完成了...")
                    }
                })

            WorkManager.getInstance(this).enqueue(periodicWorkRequest)
        }

        binding.btnStopPeriod.setOnClickListener {
            WorkManager.getInstance(this).cancelAllWorkByTag(PERIOD_TAG)
        }

        // 添加约束条件，必须满足条件，才能执行后台任务 （在连接网络，插入电源 并且 处于空闲时）
        binding.btnConstraints.setOnClickListener {
            val constraints: Constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED) // 网络已连接
                // .setRequiresCharging(true) // 充电中..
                // .setRequiresDeviceIdle(true) // 空闲时
                .build()

            val request = Builder(OnceTaskWorker::class.java)
                .setConstraints(constraints) // Request关联 约束条件
                .build()

            WorkManager.getInstance(this).enqueue(request)
        }
    }

    override fun createViewBinding(): ActivityWorkManagerBinding {
        return ActivityWorkManagerBinding.inflate(layoutInflater)
    }
}