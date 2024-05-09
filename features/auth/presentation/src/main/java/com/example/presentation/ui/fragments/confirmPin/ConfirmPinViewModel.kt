package com.example.presentation.ui.fragments.confirmPin

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.ResetPasswordRequest
import com.example.domain.models.UserLoginResponse
import com.example.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.asStateFlow

class ConfirmPinViewModel(
    private val repository: AuthRepository
) : BaseViewModel() {

    private val _ResetPasswordState = mutableUiStateFlow<UserLoginResponse>()
    val resetPasswordState = _ResetPasswordState.asStateFlow()

    fun userResetPassword(userData: ResetPasswordRequest) {
        repository.userResetPassword(userData).gatherRequest(_ResetPasswordState)
    }
}