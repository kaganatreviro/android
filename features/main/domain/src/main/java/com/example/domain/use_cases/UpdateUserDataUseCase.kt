package com.example.domain.use_cases

import com.example.domain.models.UpdateUserDataRequest
import com.example.domain.repositories.UserRepository

class UpdateUserDataUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(userData: UpdateUserDataRequest) = repository.updateUserData(userData)
}