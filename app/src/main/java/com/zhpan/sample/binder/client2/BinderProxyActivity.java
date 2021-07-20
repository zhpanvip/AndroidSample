package com.zhpan.sample.binder.client2;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.zhpan.library.BaseViewBindingActivity;

import org.jetbrains.annotations.NotNull;

import com.zhpan.sample.databinding.ActivityBinderBinding;


public class BinderProxyActivity extends BaseViewBindingActivity<ActivityBinderBinding> {

    private IGradeInterface mBinderProxy;

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinderProxy = BinderProxy.asInterface(iBinder);
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
        binding.btnBindService.setOnClickListener(view -> {
            String action = "android.intent.action.server.gradeservice";
            Intent intent = new Intent(action);
            intent.setPackage(getPackageName());
            bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
        });
        binding.btnFindGrade.setOnClickListener(view -> {
            int grade = mBinderProxy.getStudentGrade("Anna");
            Toast.makeText(BinderProxyActivity.this, "Anna grade is " + grade, Toast.LENGTH_SHORT).show();
        });
    }

    @NotNull
    @Override
    protected ActivityBinderBinding createViewBinding() {
        return ActivityBinderBinding.inflate(getLayoutInflater());
    }
}