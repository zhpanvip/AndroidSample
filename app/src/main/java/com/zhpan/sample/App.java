package com.zhpan.sample;

import android.app.Application;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.blankj.utilcode.util.ToastUtils;

/**
 * <pre>
 *   Created by zhpan on 2020/1/13.
 *   Description:        ProcessLifecycleOwner.
 * </pre>
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new LifecycleObserver() {
            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            public void onAppForeground() {
                ToastUtils.showShort("App已进入前台");
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            public void onBackground() {
                ToastUtils.showShort("App已进入后台运行");
            }
        });
    }
}
