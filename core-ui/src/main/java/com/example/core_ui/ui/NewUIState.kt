package com.example.core_ui.ui

import com.example.core.either.NetworkError

sealed class NewUIState<T> {
    class Idle<T> : NewUIState<T>()
    class Loading<T> : NewUIState<T>()
    class Error<T>(val error: NetworkError) : NewUIState<T>()
    class Success<T>(val data: T) : NewUIState<T>()
}