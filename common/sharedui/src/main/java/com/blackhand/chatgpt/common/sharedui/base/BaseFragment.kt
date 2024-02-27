package com.blackhand.chatgpt.common.sharedui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


open class BaseFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : Fragment() {
    lateinit var binding: T
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.let {

            performDataBinding(inflater, container)

        }
        return binding.root
    }

    private fun performDataBinding(inflater: LayoutInflater, container: ViewGroup?) {

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

    }
}