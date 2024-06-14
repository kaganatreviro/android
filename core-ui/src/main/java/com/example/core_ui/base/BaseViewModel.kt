package com.example.core_ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.either.Either
import com.example.core.either.NetworkError
import com.example.core_ui.ui.NewUIState
import com.example.core_ui.ui.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> mutableUiStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())
    protected fun <T> mutableNewUiStateFlow() = MutableStateFlow<NewUIState<T>>(NewUIState.Idle())

    protected fun <T> Flow<Either<String, T>>.gatherRequest(
        state: MutableStateFlow<UIState<T>>,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@gatherRequest.collect {
                when (it) {
                    is Either.Left -> state.value = UIState.Error(it.value)
                    is Either.Right -> state.value =
                        UIState.Success(it.value)
                }
            }
        }
    }

    protected fun <T> Flow<Either<NetworkError, T>>.newGatherRequest(
        state: MutableStateFlow<NewUIState<T>>,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = NewUIState.Loading()
            this@newGatherRequest.collect {
                when (it) {
                    is Either.Left -> {
                        state.value = NewUIState.Error(it.value)
                    }

                    is Either.Right -> {
                        state.value = NewUIState.Success(it.value)
                    }
                }
            }
        }
    }
    protected fun <T> MutableStateFlow<UIState<T>>.reset() {
        value = UIState.Idle()
    }
}