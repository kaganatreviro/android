package com.example.data.remote.api_services

import com.example.data.remote.dto.UserChangePasswordRequestDto
import com.example.data.remote.dto.UserForgotPasswordRequestDto
import com.example.data.remote.dto.UserLoginResponseDto
import com.example.data.remote.dto.UserResetPasswordRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {

    @POST(CLIENT_FORGOT_PASSWORD_ENDPOINT)
    suspend fun userForgotPassword(@Body userData: UserForgotPasswordRequestDto): String

    @POST(CLIENT_RESET_PASSWORD_ENDPOINT)
    suspend fun userResetPassword(@Body userData: UserResetPasswordRequestDto): UserLoginResponseDto

    @POST(CLIENT_CHANGE_PASSWORD_ENDPOINT)
    suspend fun userChangePassword(@Body userData: UserChangePasswordRequestDto): String

    companion object {
        const val CLIENT_FORGOT_PASSWORD_ENDPOINT = "api/v1/user/password_forgot/"
        const val CLIENT_RESET_PASSWORD_ENDPOINT = "api/v1/user/password_reset/"
        const val CLIENT_CHANGE_PASSWORD_ENDPOINT = "api/v1/user/password_change/"
    }
}