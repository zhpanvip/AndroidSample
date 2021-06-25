package com.zhpan.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 *
 * @author zhangpan
 * @date 2021/6/25
 */
abstract class BaseViewBindingFragment<T : ViewBinding> : Fragment() {

    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = createViewBinding(container)
        onViewBindingCreated(binding.root)
        return binding.root
    }

    abstract fun onViewBindingCreated(rootView:View?)

    abstract fun createViewBinding(container: ViewGroup?): T
}