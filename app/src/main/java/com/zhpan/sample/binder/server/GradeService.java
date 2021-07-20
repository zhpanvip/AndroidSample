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
    private final Binder mBinder = new Binder() {
        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            if (code == REQUEST_CODE) {
                String name = data.readString();
                int studentGrade = getStudentGrade(name);
                if (reply != null)
                    reply.writeInt(studentGrade);
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }

        public int getStudentGrade(String name) {
            return StudentMap.getStudentGrade(name);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
