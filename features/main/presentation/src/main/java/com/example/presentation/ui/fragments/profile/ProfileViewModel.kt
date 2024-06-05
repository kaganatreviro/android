package com.example.presentation.ui.fragments.profile

import com.example.core_ui.base.BaseViewModel
import com.example.core_ui.ui.UIState
import com.example.domain.models.UpdateUserDataRequest
import com.example.domain.models.User
import com.example.domain.use_cases.GetUserUseCase
import com.example.domain.use_cases.LogoutUseCase
import com.example.domain.use_cases.UpdateUserDataUseCase
import kotlinx.coroutines.flow.asStateFlow
import java.io.File

class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserDataUseCase: UpdateUserDataUseCase,
    private val logoutUseCase: LogoutUseCase
): BaseViewModel() {

    private val _userState = mutableUiStateFlow<User>()
    val userState = _userState.asStateFlow()

    private val _userLogoutState = mutableUiStateFlow<Unit>()
    val userLogoutState = _userLogoutState.asStateFlow()

    init {
        getUser()
    }

    private fun getUser() {
        getUserUseCase().gatherRequest(_userState)
    }

    fun updateData(name: String? = null, date: String? = null, avatar: File? = null) {
        updateUserDataUseCase(UpdateUserDataRequest(name, date, avatar))
            .gatherRequest(_userState)
    }

    fun logout() {
        logoutUseCase().gatherRequest(_userLogoutState)
    }

    fun resetUserLogoutState() {
        _userLogoutState.reset()
    }
}