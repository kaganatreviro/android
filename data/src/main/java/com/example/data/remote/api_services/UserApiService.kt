package com.example.data.remote.api_services

import com.example.data.remote.dto.UserRegisterRequestDto
import com.example.data.remote.dto.UserRegisterResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {

    @POST(CLIENT_REGISTER_ENDPOINT)
    suspend fun userRegister(@Body userData: UserRegisterRequestDto): UserRegisterResponseDto

    companion object {
        const val CLIENT_REGISTER_ENDPOINT = "api/v1/user/client_register/"
    }
}