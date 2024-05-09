package com.example.data.repositories

import com.example.core.either.Either
import com.example.data.remote.api_services.UserApiService
import com.example.domain.models.User
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val apiService: UserApiService
) : UserRepository {

    override fun getUser(): Flow<Either<String, User>> = makeNetworkRequest {
        apiService.getUser().toDomain()
    }
}