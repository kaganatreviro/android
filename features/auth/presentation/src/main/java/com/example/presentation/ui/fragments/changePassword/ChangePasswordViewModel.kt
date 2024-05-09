package com.example.presentation.ui.fragments.changePassword

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.ChangePasswordRequest
import com.example.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.asStateFlow

class ChangePasswordViewModel(private val repository: AuthRepository) : BaseViewModel() {
    private val _changePasswordState = mutableUiStateFlow<ChangePasswordRequest>()
    val changePasswordState = _changePasswordState.asStateFlow()

    fun userChangePassword(userData: ChangePasswordRequest) {
        repository.userChangePassword(userData)
    }
}