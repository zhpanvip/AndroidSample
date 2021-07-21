package com.zhpan.sample.binder.server;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zhpan.sample.binder.StudentMap;
import com.zhpan.sample.binder.client2.IGradeInterface;

import static com.zhpan.sample.binder.server.GradeService.REQUEST_CODE;

/**
 * @author zhangpan
 * @date 2021/7/20
 */
public class GradeBinder extends Binder implements IGradeInterface {

    @Override
    public int getStudentGrade(String name) {
        return StudentMap.getStudentGrade(name);
    }

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
}
