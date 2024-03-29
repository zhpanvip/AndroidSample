package com.zhpan.sample.binder.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.blankj.utilcode.util.ToastUtils;
import com.zhpan.library.BaseViewBindingActivity;
import com.zhpan.sample.databinding.ActivityBinderBinding;

import org.jetbrains.annotations.NotNull;

import static com.zhpan.sample.binder.server.GradeService.REQUEST_CODE;

public class BinderActivity extends BaseViewBindingActivity<ActivityBinderBinding> {

    private IBinder mRemoteBinder;

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mRemoteBinder = iBinder;
            ToastUtils.showShort("已连接远程服务，可以查询成绩了。");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mRemoteBinder = null;
            ToastUtils.showShort("已断开远程服务");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.btnBindService.setOnClickListener(view -> bindGradeService());
        binding.btnFindGrade.setOnClickListener(view -> ToastUtils.showShort("Anna grade is "
                + getStudentGrade("Anna")));
    }

    private void bindGradeService() {
        String action = "android.intent.action.server.gradeservice";
        Intent intent = new Intent(action);
        intent.setPackage(getPackageName());
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    private int getStudentGrade(String name) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        int grade = 0;
        data.writeString(name);
        try {
            if (mRemoteBinder == null) {
                throw new IllegalStateException("Need Bind Remote Server...");
            }
            mRemoteBinder.transact(REQUEST_CODE, data, reply, 0);
            grade = reply.readInt();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return grade;
    }

    @NotNull
    @Override
    protected ActivityBinderBinding createViewBinding() {
        return ActivityBinderBinding.inflate(getLayoutInflater());
    }
}