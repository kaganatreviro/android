package com.example.domain.use_cases

import com.example.domain.models.UserLoginRequest
import com.example.domain.repositories.AuthRepository

class UserLoginUseCase(
    private val userRepository: AuthRepository
) {
    operator fun invoke(user: UserLoginRequest) = userRepository.userLogin(user)
}