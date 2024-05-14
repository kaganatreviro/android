package com.example.presentation.ui.fragments.profile

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.UpdateUserDataRequest
import com.example.domain.models.User
import com.example.domain.use_cases.GetUserUseCase
import com.example.domain.use_cases.UpdateUserDataUseCase
import kotlinx.coroutines.flow.asStateFlow
import java.io.File

class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserDataUseCase: UpdateUserDataUseCase,
): BaseViewModel() {

    private val _userState = mutableUiStateFlow<User>()
    val userState = _userState.asStateFlow()

//    private val _updateUserDataState = mutableUiStateFlow<User>()
//    val updateUserDataState = _updateUserDataState.asStateFlow()

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
}