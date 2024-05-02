package com.example.data.repositories

import com.example.core.either.Either
import com.example.data.remote.api_services.UserApiService
import com.example.data.remote.dto.toDto
import com.example.domain.models.UserRegisterRequest
import com.example.domain.models.UserRegisterResponse
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val apiService: UserApiService
) : UserRepository {

    override fun userRegister(userData: UserRegisterRequest): Flow<Either<String, UserRegisterResponse>> =
        makeNetworkRequest { apiService.userRegister(userData.toDto()).toDomain() }
}