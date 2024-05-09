package com.example.domain.use_cases

import com.example.domain.repositories.UserRepository

class LogoutUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke() = userRepository.logout()
}