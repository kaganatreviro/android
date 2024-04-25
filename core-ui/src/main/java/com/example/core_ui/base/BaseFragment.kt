package com.example.core_ui.base

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(@LayoutRes layoutId: Int) :
    Fragment(layoutId) {

    protected abstract val binding: VB
    protected abstract val viewModel: VM
    private lateinit var callback: OnBackPressedCallback
    open var backPressedTime: Long = 0
    open val doubleBackPressInterval = 2000

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupListeners()
        launchObservers()
    }

    private fun setBackButtonDispatcher() {
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    protected open fun initialize() {}
    protected open fun setupListeners() {}
    protected open fun launchObservers() {}
    protected open fun onBackPressed() {}
}