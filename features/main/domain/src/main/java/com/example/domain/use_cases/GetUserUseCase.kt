package com.example.domain.use_cases

import com.example.domain.repositories.UserRepository

class GetUserUseCase(
    private val repository: UserRepository
) {
    operator fun invoke() = repository.getUser()
}