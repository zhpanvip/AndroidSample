package com.zhpan.sample.binder;

import android.content.Intent;
import android.os.Bundle;

import com.zhpan.library.BaseViewBindingActivity;
import com.zhpan.sample.binder.aidl.AidlActivity;
import com.zhpan.sample.binder.client.BinderActivity;
import com.zhpan.sample.binder.client2.BinderProxyActivity;
import com.zhpan.sample.databinding.ActivityBinderEntryBinding;

import org.jetbrains.annotations.NotNull;

public class BinderEntryActivity extends BaseViewBindingActivity<ActivityBinderEntryBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.itemBinder.setOnClickListener(v ->
                startActivity(new Intent(BinderEntryActivity.this, BinderActivity.class)));
        binding.itemBinderProxy.setOnClickListener(v ->
                startActivity(new Intent(BinderEntryActivity.this, BinderProxyActivity.class)));
        binding.itemAidl.setOnClickListener(v ->
                startActivity(new Intent(BinderEntryActivity.this, AidlActivity.class)));
    }

    @NotNull
    @Override
    protected ActivityBinderEntryBinding createViewBinding() {
        return ActivityBinderEntryBinding.inflate(getLayoutInflater());
    }
}