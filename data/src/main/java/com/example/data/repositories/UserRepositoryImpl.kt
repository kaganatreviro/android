package com.example.data.repositories

import com.example.core.either.Either
import com.example.data.local.prefs.TokenPrefs
import com.example.data.remote.api_services.UserApiService
import com.example.data.remote.dto.toDto
import com.example.domain.models.ChangePasswordRequest
import com.example.domain.models.ForgotPasswordRequest
import com.example.domain.models.ResetPasswordRequest
import com.example.domain.models.UserLoginResponse
import com.example.domain.models.UserRegisterRequest
import com.example.domain.models.UserRegisterResponse
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val apiService: UserApiService,
    private val tokenPrefs: TokenPrefs
) : UserRepository {

    override fun userRegister(userData: UserRegisterRequest): Flow<Either<String, UserRegisterResponse>> =
        makeNetworkRequest {
            apiService.userRegister(userData.toDto()).toDomain().also {
                tokenPrefs.access = it.tokens.access
                tokenPrefs.refresh = it.tokens.refresh
            }
        }

    override fun userForgotPassword(userData: ForgotPasswordRequest): Flow<Either<String, String>> =
        makeNetworkRequest { apiService.userForgotPassword(userData.toDto()) }

    override fun userResetPassword(userData: ResetPasswordRequest): Flow<Either<String, UserLoginResponse>> =
        makeNetworkRequest { apiService.userResetPassword(userData.toDto()).toDomain() }

    override fun userChangePassword(userData: ChangePasswordRequest): Flow<Either<String, String>> =
        makeNetworkRequest { apiService.userChangePassword(userData.toDto()) }
}