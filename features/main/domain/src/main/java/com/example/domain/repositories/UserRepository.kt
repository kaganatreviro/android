package com.example.domain.repositories

import com.example.core.either.Either
import com.example.domain.models.UpdateUserDataRequest
import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UserRepository {

    fun getUser(): Flow<Either<String, User>>
    fun updateUserData(userData: UpdateUserDataRequest): Flow<Either<String, User>>
    fun logout(): Either<String, Boolean>
}