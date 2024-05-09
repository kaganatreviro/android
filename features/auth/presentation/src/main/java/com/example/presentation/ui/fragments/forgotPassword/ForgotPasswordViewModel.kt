package com.example.presentation.ui.fragments.forgotPassword

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.ForgotPasswordRequest
import com.example.domain.models.ResetPasswordRequest
import com.example.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.asStateFlow

class ForgotPasswordViewModel(
    private val repository: AuthRepository
) : BaseViewModel() {

    private val _ForgotPasswordState = mutableUiStateFlow<ForgotPasswordRequest>()
    val forgotPasswordState = _ForgotPasswordState.asStateFlow()
    private val _ResetPasswordState = mutableUiStateFlow<String>()
    val resetPasswordState = _ResetPasswordState.asStateFlow()

    fun userForgotPassword(userData: ForgotPasswordRequest) {
        repository.userForgotPassword(userData)
    }

    fun userResetPassword(userData: ResetPasswordRequest) {
        repository.userResetPassword(userData).gatherRequest(_ResetPasswordState)
    }
}