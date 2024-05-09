package com.example.domain.repositories

import com.example.core.either.Either
import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(): Flow<Either<String, User>>
}