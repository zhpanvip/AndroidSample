package com.zhpan.sample.binder.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zhpan.sample.binder.StudentMap;


/**
 * @author zhangpan
 * @date 2021/7/20
 */
public class GradeService extends Service {

    public static final int REQUEST_CODE=1000;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new GradeBinder();
    }
}
