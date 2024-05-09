package com.example.presentation.ui.fragments.profile

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.User
import com.example.domain.use_cases.GetUserUseCase
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase
): BaseViewModel() {

    private val _userState = mutableUiStateFlow<User>()
    val userState = _userState.asStateFlow()

    init {
        getUser()
    }

    private fun getUser() {
        getUserUseCase().gatherRequest(_userState)
    }
}