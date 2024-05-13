package com.example.presentation.ui.fragments

import com.example.core.either.Either
import com.example.core_ui.base.BaseViewModel
import com.example.domain.use_cases.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val logoutUseCase: LogoutUseCase
): BaseViewModel() {

    private val _navigateToAuthModule = MutableStateFlow<Either<String, Boolean>?>(null)
    val navigateToAuthModule = _navigateToAuthModule.asStateFlow()

    fun logout() {
        _navigateToAuthModule.value = logoutUseCase.invoke()
    }
}