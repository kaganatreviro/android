package com.example.data.repositories

import com.example.core.either.Either
import com.example.core.either.NetworkError
import com.example.data.local.prefs.TokenPrefs
import com.example.data.remote.api_services.AuthApiService
import com.example.data.remote.dto.toDto
import com.example.domain.models.ChangePasswordRequest
import com.example.domain.models.ForgotPasswordRequest
import com.example.domain.models.ResetPasswordRequest
import com.example.domain.models.UserLoginRequest
import com.example.domain.models.UserLoginResponse
import com.example.domain.models.UserRegisterRequest
import com.example.domain.models.UserRegisterResponse
import com.example.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val tokenPrefs: TokenPrefs,
): AuthRepository {

    override fun userLogin(userData: UserLoginRequest): Flow<Either<NetworkError, Unit>> =
        makeNetworkRequestWithUnitReturnType {
            authApiService.userLogin(userData.toDto())
        }

    override fun userRegister(userData: UserRegisterRequest): Flow<Either<String, UserRegisterResponse>> =
        makeNetworkRequest {
            authApiService.userRegister(userData.toDto()).toDomain().also {
                tokenPrefs.access = it.tokens.access
                tokenPrefs.refresh = it.tokens.refresh
                tokenPrefs.userEmail = userData.email
            }
        }

    override fun userForgotPassword(userData: ForgotPasswordRequest): Flow<Either<String, String>> =
        makeNetworkRequest { authApiService.userForgotPassword(userData.toDto()) }

    override fun userResetPassword(userData: ResetPasswordRequest): Flow<Either<String, String>> =
        makeNetworkRequest { authApiService.userResetPassword(userData.toDto()) }

    override fun userChangePassword(userData: ChangePasswordRequest): Flow<Either<String, String>> =
        makeNetworkRequest { authApiService.userChangePassword(userData.toDto()) }
}