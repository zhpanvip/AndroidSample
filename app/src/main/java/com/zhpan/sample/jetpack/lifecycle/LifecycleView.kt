package com.zhpan.sample.jetpack.lifecycle

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

import com.blankj.utilcode.util.LogUtils

/**
 * <pre>
 * Created by zhangpan on 2020-01-10.
 * Description:
</pre> *
 */
class LifecycleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr), LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    internal fun onPause() {
        LogUtils.e("LifecycleView onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    internal fun onResume() {
        LogUtils.e("LifecycleView onResume")
    }
}
