package com.example.data.remote.api_services

import com.example.data.remote.dto.RefreshTokenRequestDto
import com.example.data.remote.dto.TokensDto
import com.example.data.remote.dto.UserChangePasswordRequestDto
import com.example.data.remote.dto.UserForgotPasswordRequestDto
import com.example.data.remote.dto.UserLoginRequestDto
import com.example.data.remote.dto.UserLoginResponseDto
import com.example.data.remote.dto.UserRegisterRequestDto
import com.example.data.remote.dto.UserRegisterResponseDto
import com.example.data.remote.dto.UserResetPasswordRequestDto
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApiService {

    @POST(CLIENT_LOGIN_ENDPOINT)
    suspend fun userLogin(@Body userData: UserLoginRequestDto): UserLoginResponseDto

    @POST(CLIENT_REGISTER_ENDPOINT)
    suspend fun userRegister(@Body userData: UserRegisterRequestDto): UserRegisterResponseDto

    @POST(CLIENT_TOKEN_REFRESH)
    suspend fun refreshToken(@Body refreshToken: RefreshTokenRequestDto): TokensDto

    @POST(CLIENT_FORGOT_PASSWORD_ENDPOINT)
    suspend fun userForgotPassword(@Body userData: UserForgotPasswordRequestDto): String

    @Headers("Content-Type: application/json")
    @POST(CLIENT_RESET_PASSWORD_ENDPOINT)
    suspend fun userResetPassword(@Body userData: UserResetPasswordRequestDto): String

    @POST(CLIENT_CHANGE_PASSWORD_ENDPOINT)
    suspend fun userChangePassword(@Body userData: UserChangePasswordRequestDto): String

    companion object {
        const val CLIENT_LOGIN_ENDPOINT = "/api/v1/user/client/auth/token/"
        const val CLIENT_REGISTER_ENDPOINT = "api/v1/user/client/register/"
        const val CLIENT_TOKEN_REFRESH = "api/v1/user/auth/token/refresh/"
        const val CLIENT_FORGOT_PASSWORD_ENDPOINT = "api/v1/user/client/password/forgot/"
        const val CLIENT_RESET_PASSWORD_ENDPOINT = "api/v1/user/client/password/reset/"
        const val CLIENT_CHANGE_PASSWORD_ENDPOINT = "api/v1/user/client/password/change/"
    }
}