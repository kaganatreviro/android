package com.example.presentation.ui.fragments.sign_up

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.UserRegisterRequest
import com.example.domain.models.UserRegisterResponse
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel(
    private val repository: UserRepository
): BaseViewModel() {

    private val _registerState = mutableUiStateFlow<UserRegisterResponse>()
    val registerState = _registerState.asStateFlow()

    fun userRegister(userData: UserRegisterRequest) {
        repository.userRegister(userData).gatherRequest(_registerState)
    }
}