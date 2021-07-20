package com.zhpan.sample.binder.aidl;

import android.os.Bundle;

import com.zhpan.library.BaseViewBindingActivity;
import com.zhpan.sample.databinding.ActivityAidlActivityBinding;

import org.jetbrains.annotations.NotNull;

public class AIDLActivity extends BaseViewBindingActivity<ActivityAidlActivityBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NotNull
    @Override
    protected ActivityAidlActivityBinding createViewBinding() {
        return ActivityAidlActivityBinding.inflate(getLayoutInflater());
    }
}