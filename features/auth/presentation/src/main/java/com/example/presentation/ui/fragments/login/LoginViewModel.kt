package com.example.presentation.ui.fragments.login

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.UserLoginRequest
import com.example.domain.use_cases.UserLoginUseCase
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val loginUseCase: UserLoginUseCase
) : BaseViewModel() {

    private val _loginState = mutableUiStateFlow<Unit>()
    val loginState = _loginState.asStateFlow()

    fun userLogin(email: String, password: String) {
        loginUseCase(UserLoginRequest(email, password)).gatherRequest(_loginState)
    }
}