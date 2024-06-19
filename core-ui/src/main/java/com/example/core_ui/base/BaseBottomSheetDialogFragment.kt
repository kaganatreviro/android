package com.example.core_ui.base

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.core.either.NetworkError
import com.example.core_ui.ui.NewUIState
import com.example.core_ui.ui.UIState
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseBottomSheetDialogFragment<Binding : ViewBinding, ViewModel : BaseViewModel>(
    @LayoutRes private val layoutId: Int
) : BottomSheetDialogFragment() {

    protected abstract val binding: Binding
    protected abstract val viewModel: ViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { setupBottomSheetBackgroundTransparent(it) }
        return dialog
    }

    private fun setupBottomSheetBackgroundTransparent(dialogInterface: DialogInterface) {
        val bottomSheetDialog = dialogInterface as BottomSheetDialog
        val bottomSheet = bottomSheetDialog.findViewById<View>(
            com.google.android.material.R.id.design_bottom_sheet
        ) ?: return
        bottomSheet.setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        launchObservers()
    }

    protected open fun initialize() {}
    protected open fun setupListeners() {}
    protected open fun launchObservers() {}

    protected fun <T> StateFlow<UIState<T>>.spectateUiState(
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
                        loading?.invoke(it)
                    }

                    is UIState.Error -> {
                        error?.invoke(it.error)
                    }

                    is UIState.Success -> {
                        success?.invoke(it.data)
                    }
                }
            }
        }
    }

    protected fun <T> StateFlow<NewUIState<T>>.spectateNewUiState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        success: ((data: T) -> Unit)? = null,
        loading: ((data: NewUIState.Loading<T>) -> Unit)? = null,
        error: ((error: NetworkError) -> Unit)? = null,
        idle: ((idle: NewUIState.Idle<T>) -> Unit)? = null,
    ) {
        safeFlowGather(lifecycleState) {
            collect {
                when (it) {
                    is NewUIState.Idle -> idle?.invoke(it)
                    is NewUIState.Loading -> {
                        loading?.invoke(it)
                    }

                    is NewUIState.Error -> {
                        error?.invoke(it.error)
                    }

                    is NewUIState.Success -> {
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
}