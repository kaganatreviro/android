package com.example.domain.repositories

import com.example.core.either.Either
import com.example.domain.models.ChangePasswordRequest
import com.example.domain.models.ForgotPasswordRequest
import com.example.domain.models.ResetPasswordRequest
import com.example.domain.models.UserLoginResponse
import com.example.domain.models.UserRegisterRequest
import com.example.domain.models.UserRegisterResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun userRegister(userData: UserRegisterRequest): Flow<Either<String, UserRegisterResponse>>
    fun userForgotPassword(userData: ForgotPasswordRequest): Flow<Either<String, String>>
    fun userResetPassword(userData: ResetPasswordRequest): Flow<Either<String, UserLoginResponse>>
    fun userChangePassword(userData: ChangePasswordRequest): Flow<Either<String, String>>
}