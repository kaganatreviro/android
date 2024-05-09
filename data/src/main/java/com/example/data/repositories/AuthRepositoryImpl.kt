package com.example.data.repositories

import com.example.core.either.Either
import com.example.data.local.prefs.TokenPrefs
import com.example.data.remote.api_services.AuthApiService
import com.example.data.remote.dto.toDto
import com.example.domain.models.UserLoginRequest
import com.example.domain.models.UserRegisterRequest
import com.example.domain.models.UserRegisterResponse
import com.example.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val tokenPrefs: TokenPrefs,
): AuthRepository {

    override fun userLogin(userData: UserLoginRequest): Flow<Either<String, Unit>> =
        makeNetworkRequest {
            authApiService.userLogin(userData.toDto()).also {
                tokenPrefs.access = it.access
                tokenPrefs.refresh = it.refresh
            }
        }

    override fun userRegister(userData: UserRegisterRequest): Flow<Either<String, UserRegisterResponse>> =
        makeNetworkRequest {
            authApiService.userRegister(userData.toDto()).toDomain().also {
                tokenPrefs.access = it.tokens.access
                tokenPrefs.refresh = it.tokens.refresh
            }
        }
}