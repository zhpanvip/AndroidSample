package com.zhpan.sample.binder.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.zhpan.sample.binder.StudentMap;
import com.zhpan.sample.binder.aidl.IGradeService;
import com.zhpan.sample.binder.server.GradeBinder;


/**
 * @author zhangpan
 * @date 2021/7/20
 */
public class AidlGradeService extends Service {

    private final IBinder mBinder = new IGradeService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getStudentGrade(String name) throws RemoteException {
            return StudentMap.getStudentGrade(name);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
