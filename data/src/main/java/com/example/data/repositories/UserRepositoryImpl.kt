package com.example.data.repositories

import com.example.core.either.Either
import com.example.core.either.NetworkError
import com.example.data.local.prefs.TokenPrefs
import com.example.data.remote.api_services.UserApiService
import com.example.data.remote.dto.LogoutRequestDto
import com.example.domain.models.UpdateUserDataRequest
import com.example.domain.models.User
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class UserRepositoryImpl(
    private val apiService: UserApiService,
    private val tokenPrefs: TokenPrefs,
) : UserRepository {

    override fun getUser(): Flow<Either<NetworkError, User>> = makeNetworkRequestWithMapping {
        apiService.getUser()
    }

    override fun updateUserData(userData: UpdateUserDataRequest): Flow<Either<NetworkError, User>> =
        makeNetworkRequestWithMapping {
            val userName = userData.name?.toRequestBody("text/plain".toMediaTypeOrNull())
            val userDate = userData.date?.toRequestBody("text/plain".toMediaTypeOrNull())
            val userAvatar = userData.avatar?.asRequestBody("image/jpeg".toMediaTypeOrNull())
            apiService.updateUserData(userName, userDate, userAvatar)
        }

    override fun logout(): Flow<Either<String, Unit>> = flow<Either<String, Unit>> {
        apiService.logout(LogoutRequestDto(tokenPrefs.refresh!!)).also {
            emit(Either.Right(it))
        }
        tokenPrefs.clearUserData()
    }.flowOn(Dispatchers.IO).catch {
        it.printStackTrace()
        emit(Either.Left(value = it.message ?: "Unknown error"))
    }
}