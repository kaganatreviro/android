package com.example.presentation.ui.fragments.forgotPassword

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.ForgotPasswordRequest
import com.example.domain.models.ResetPasswordRequest
import com.example.domain.models.UserRegisterResponse
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.asStateFlow

class ForgotPasswordViewModel(
    private val repository: UserRepository
) : BaseViewModel() {

    private val _forgotPasswordState = mutableUiStateFlow<String>()
    val forgotPasswordState = _forgotPasswordState.asStateFlow()

    private val _resetPasswordState = mutableUiStateFlow<UserRegisterResponse>()
    val resetPasswordState = _resetPasswordState.asStateFlow()

    fun userForgotPassword(userData: ForgotPasswordRequest) {
        repository.userForgotPassword(userData).gatherRequest(_forgotPasswordState)
    }

    fun userResetPassword(userData: ResetPasswordRequest) {
        repository.userResetPassword(userData).gatherRequest(_resetPasswordState)
    }
}