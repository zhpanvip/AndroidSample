package com.zhpan.library

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingActivity<T : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createViewBinding()
        super.setContentView(binding.root)
    }

    override fun setContentView(layoutResID: Int) {
        throw IllegalArgumentException(
            "The method createViewBinding() has created a new content view to be add，So you should not set again."
        )
    }

    override fun setContentView(contentView: View?) {
        throw IllegalArgumentException(
            "The method createViewBinding() has created a new content view to be add，So you should not set again."
        )
    }

    protected abstract fun createViewBinding(): T
}