package com.example.presentation.ui.fragments.forgotPassword

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.ForgotPasswordRequest
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.asStateFlow

class ForgotPasswordViewModel(private val repository: UserRepository) : BaseViewModel() {
    private val _ForgotPasswordState = mutableUiStateFlow<ForgotPasswordRequest>()
    val forgotPasswordState = _ForgotPasswordState.asStateFlow()

    fun userForgotPassword(userData: ForgotPasswordRequest) {
        repository.userForgotPassword(userData).gatherRequest()
    }
}