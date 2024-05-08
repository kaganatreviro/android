package com.example.presentation.ui.fragments.changePassword

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.ChangePasswordRequest
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.asStateFlow

class ChangePasswordViewModel(private val repository: UserRepository) : BaseViewModel() {
    private val _ChangePasswordState = mutableUiStateFlow<ChangePasswordRequest>()
    val changePasswordState = _ChangePasswordState.asStateFlow()

    fun userChangePassword(userData: ChangePasswordRequest) {
        repository.userChangePassword(userData)
    }
}