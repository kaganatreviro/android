package com.example.domain.use_cases

import com.example.domain.models.UserLoginRequest
import com.example.domain.repositories.UserRepository

class UserLoginUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(user: UserLoginRequest) = userRepository.userLogin(user)
}