package com.example.data.remote.api_services

import com.example.data.remote.dto.LogoutRequestDto
import com.example.data.remote.dto.UserDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface UserApiService {

    @GET(GET_USER_ENDPOINT)
    suspend fun getUser(): Response<UserDto>

    @Multipart
    @PUT(UPDATE_USER_DATA_ENDPOINT)
    suspend fun updateUserData(
        @Part("name") name: RequestBody?,
        @Part("date_of_birth") date: RequestBody?,
        @Part("avatar\"; filename = \"pp.png") imageFile: RequestBody?,
    ): Response<UserDto>

    @POST(LOGOUT_ENDPOINT)
    suspend fun logout(
        @Body refresh: LogoutRequestDto
    )

    companion object {
        const val GET_USER_ENDPOINT = "api/v1/user/users/profile/"
        const val UPDATE_USER_DATA_ENDPOINT = "api/v1/user/users/profile/"
        const val LOGOUT_ENDPOINT = "api/v1/user/auth/logout/"
    }
}