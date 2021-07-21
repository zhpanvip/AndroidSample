package com.zhpan.sample.binder.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import com.blankj.utilcode.util.ToastUtils;
import com.zhpan.library.BaseViewBindingActivity;
import com.zhpan.sample.databinding.ActivityBinderBinding;

import org.jetbrains.annotations.NotNull;

public class AidlActivity extends BaseViewBindingActivity<ActivityBinderBinding> {

    private IGradeService mBinderProxy;

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinderProxy = IGradeService.Stub.asInterface(iBinder);
            ToastUtils.showShort("已连接远程服务，可以查询成绩了。");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBinderProxy = null;
            ToastUtils.showShort("已断开远程服务");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.btnBindService.setOnClickListener(view -> bindGradeService());
        binding.btnFindGrade.setOnClickListener(view -> getStudentGrade("Anna"));
    }

    private void getStudentGrade(String name) {
        int grade = 0;
        try {
            grade = mBinderProxy.getStudentGrade(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        ToastUtils.showShort("Anna grade is " + grade);
    }

    private void bindGradeService() {
        String action = "android.intent.action.server.aidl.gradeservice";
        Intent intent = new Intent(action);
        intent.setPackage(getPackageName());
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @NotNull
    @Override
    protected ActivityBinderBinding createViewBinding() {
        return ActivityBinderBinding.inflate(getLayoutInflater());
    }
}