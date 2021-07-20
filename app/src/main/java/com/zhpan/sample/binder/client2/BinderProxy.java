package com.zhpan.sample.binder.client2;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.blankj.utilcode.util.LogUtils;

import static com.zhpan.sample.binder.server.GradeService.REQUEST_CODE;

/**
 * @author zhangpan
 * @date 2021/7/20
 */
public class BinderProxy implements IGradeInterface {
    private final IBinder mBinder;

    private BinderProxy(IBinder binder) {
        mBinder = binder;
    }

    @Override
    public int getStudentGrade(String name) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        int grade = 0;
        data.writeString(name);
        try {
            if (mBinder == null) {
                throw new IllegalStateException("Need Bind Remote Server...");
            }
            mBinder.transact(REQUEST_CODE, data, reply, 0);
            grade = reply.readInt();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return grade;
    }

    public static IGradeInterface asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        if (iBinder instanceof IGradeInterface) {
            LogUtils.e("当前进程");
            return (IGradeInterface) iBinder;
        } else {
            LogUtils.e("远程进程");
            return new BinderProxy(iBinder);
        }
    }

}
