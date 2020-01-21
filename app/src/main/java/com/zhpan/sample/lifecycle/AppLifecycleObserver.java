package com.zhpan.sample.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.blankj.utilcode.util.ToastUtils;

/**
 * <pre>
 *   Created by zhpan on 2020/1/13.
 *   Description:
 * </pre>
 */
public class AppLifecycleObserver implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onAppForeground() {
        ToastUtils.showShort("App已进入前台");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onBackground() {
        ToastUtils.showShort("App已进入后台运行");
    }

}
