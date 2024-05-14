package com.example.data.repositories

import com.example.core.either.Either
import com.example.data.local.prefs.TokenPrefs
import com.example.data.remote.api_services.UserApiService
import com.example.domain.models.UpdateUserDataRequest
import com.example.domain.models.User
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class UserRepositoryImpl(
    private val apiService: UserApiService,
    private val tokenPrefs: TokenPrefs,
) : UserRepository {

    override fun getUser(): Flow<Either<String, User>> = makeNetworkRequest {
        apiService.getUser().toDomain()
    }

    override fun updateUserData(userData: UpdateUserDataRequest): Flow<Either<String, User>> =
        makeNetworkRequest {
            val userName = userData.name?.toRequestBody("text/plain".toMediaTypeOrNull())
            val userDate = userData.name?.toRequestBody("text/plain".toMediaTypeOrNull())
            val userAvatar = userData.avatar?.asRequestBody("image/jpeg".toMediaTypeOrNull())
            apiService.updateUserData(userName, userDate, userAvatar).toDomain()
        }

    override fun logout(): Either<String, Boolean> {
        return try {
            tokenPrefs.clearUserData()
            Either.Right(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Left(e.localizedMessage) as Either<String, Boolean>
        }
    }
}