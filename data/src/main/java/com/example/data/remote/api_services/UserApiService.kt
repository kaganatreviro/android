package com.example.data.remote.api_services

import com.example.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {

    @GET(GET_USER_ENDPOINT)
    suspend fun getUser(): UserDto

    companion object {
        const val GET_USER_ENDPOINT = "api/v1/user/"
    }
}