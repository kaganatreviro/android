package com.example.presentation.ui.fragments.profile.edit_profile

import android.util.Log
import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.UpdateUserDataRequest
import com.example.domain.models.User
import com.example.domain.repositories.UserRepository
import com.example.domain.use_cases.GetUserUseCase
import kotlinx.coroutines.flow.asStateFlow
import java.io.File

class EditProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _userState = mutableUiStateFlow<User>()
    val userState = _userState.asStateFlow()

    private val _updateUserDataState = mutableUiStateFlow<User>()
    val updateUserDataState = _updateUserDataState.asStateFlow()

    init {
        getUser()
    }

    fun updateData(name: String? = null, date: String? = null, avatar: File? = null) {
        userRepository.updateUserData(UpdateUserDataRequest(name, date, avatar))
            .gatherRequest(_updateUserDataState)
    }

    private fun getUser() {
        getUserUseCase().gatherRequest(_userState)
    }
}