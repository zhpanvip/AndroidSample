package com.zhpan.sample;

import android.app.Application;

import androidx.lifecycle.ProcessLifecycleOwner;

import com.zhpan.sample.lifecycle.AppLifecycleObserver;

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
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new AppLifecycleObserver());
    }
}
