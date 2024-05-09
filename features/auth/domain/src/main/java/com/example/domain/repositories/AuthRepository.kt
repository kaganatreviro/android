package com.example.domain.repositories

import com.example.core.either.Either
import com.example.domain.models.UserLoginRequest
import com.example.domain.models.UserRegisterRequest
import com.example.domain.models.UserRegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun userLogin(userData: UserLoginRequest): Flow<Either<String, Unit>>
    fun userRegister(userData: UserRegisterRequest): Flow<Either<String, UserRegisterResponse>>
}