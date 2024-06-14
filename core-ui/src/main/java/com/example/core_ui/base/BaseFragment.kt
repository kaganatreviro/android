package com.example.core_ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.core_ui.extensions.setupUIToHideKeyboardOnTouch
import com.example.core_ui.ui.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> :
    Fragment() {

    lateinit var binding: VB
    protected abstract val viewModel: VM
    private lateinit var callback: OnBackPressedCallback
    open var backPressedTime: Long = 0
    open val doubleBackPressInterval = 2000
    open var subscriptionStatus: Boolean = false
    protected abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUIToHideKeyboardOnTouch(view)
        initialize()
        setupListeners()
        launchObservers()
    }

    protected open fun initialize() {}
    protected open fun setupListeners() {}
    protected open fun launchObservers() {}
    protected open fun onBackPressed() {}

    private fun setBackButtonDispatcher() {
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    protected fun <T> StateFlow<UIState<T>>.spectateUiState(
        showLoader: Boolean = true,
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        success: ((data: T) -> Unit)? = null,
        loading: ((data: UIState.Loading<T>) -> Unit)? = null,
        error: ((error: String) -> Unit)? = null,
        idle: ((idle: UIState.Idle<T>) -> Unit)? = null,
    ) {
        safeFlowGather(lifecycleState) {
            collect {
                when (it) {
                    is UIState.Idle -> idle?.invoke(it)
                    is UIState.Loading -> {
                        if (showLoader)
                            showLoading()
                        loading?.invoke(it)
                    }

                    is UIState.Error -> {
                        if (showLoader)
                            hideLoading()
                        error?.invoke(it.error)
                    }

                    is UIState.Success -> {
                        if (showLoader)
                            hideLoading()
                        success?.invoke(it.data)
                    }
                }
            }
        }
    }

    private fun safeFlowGather(
        lifecycleState: Lifecycle.State,
        gather: suspend () -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                gather()
            }
        }
    }

    private fun showLoading() {
        requireActivity().supportFragmentManager.showLoading()
    }

    private fun hideLoading() {
        requireActivity().supportFragmentManager.hideLoading()
    }
}