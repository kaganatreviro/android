package com.example.presentation.ui.fragments.changePassword

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.ChangePasswordRequest
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.asStateFlow

class ChangePasswordViewModel(private val repository: UserRepository) : BaseViewModel() {
    private val _changePasswordState = mutableUiStateFlow<String>()
    val changePasswordState = _changePasswordState.asStateFlow()

    fun userChangePassword(userData: ChangePasswordRequest) {
        repository.userChangePassword(userData).gatherRequest(_changePasswordState)
    }
}