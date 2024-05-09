package com.example.data.remote.api_services

import com.example.data.remote.dto.RefreshTokenRequestDto
import com.example.data.remote.dto.TokensDto
import com.example.data.remote.dto.UserLoginRequestDto
import com.example.data.remote.dto.UserLoginResponseDto
import com.example.data.remote.dto.UserRegisterRequestDto
import com.example.data.remote.dto.UserRegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST(CLIENT_LOGIN_ENDPOINT)
    suspend fun userLogin(@Body userData: UserLoginRequestDto): UserLoginResponseDto

    @POST(CLIENT_REGISTER_ENDPOINT)
    suspend fun userRegister(@Body userData: UserRegisterRequestDto): UserRegisterResponseDto

    @POST(CLIENT_TOKEN_REFRESH)
    suspend fun refreshToken(@Body refreshToken: RefreshTokenRequestDto): TokensDto

    companion object {
        const val CLIENT_LOGIN_ENDPOINT = "api/v1/user/token/"
        const val CLIENT_REGISTER_ENDPOINT = "api/v1/user/client_register/"
        const val CLIENT_TOKEN_REFRESH = "api/v1/user/token/refresh/"
    }
}