package com.example.data.remote.api_services

import com.example.data.remote.dto.UserChangePasswordRequestDto
import com.example.data.remote.dto.UserForgotPasswordRequestDto
import com.example.data.remote.dto.UserLoginRequestDto
import com.example.data.remote.dto.UserLoginResponseDto
import com.example.data.remote.dto.UserRegisterRequestDto
import com.example.data.remote.dto.UserRegisterResponseDto
import com.example.data.remote.dto.UserResetPasswordRequestDto
import com.example.domain.models.UserLoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {

    @POST(CLIENT_LOGIN_ENDPOINT)
    suspend fun userLogin(@Body userData: UserLoginRequestDto): UserLoginResponseDto

    @POST(CLIENT_REGISTER_ENDPOINT)
    suspend fun userRegister(@Body userData: UserRegisterRequestDto): UserRegisterResponseDto

    @POST(CLIENT_FORGOT_PASSWORD_ENDPOINT)
    suspend fun userForgotPassword(@Body userData: UserForgotPasswordRequestDto): String

    @POST(CLIENT_RESET_PASSWORD_ENDPOINT)
    suspend fun userResetPassword(@Body userData: UserResetPasswordRequestDto): UserLoginResponseDto

    @POST(CLIENT_CHANGE_PASSWORD_ENDPOINT)
    suspend fun userChangePassword(@Body userData: UserChangePasswordRequestDto): String

    companion object {
        const val CLIENT_LOGIN_ENDPOINT = "api/v1/user/token/"
        const val CLIENT_REGISTER_ENDPOINT = "api/v1/user/client_register/"
        const val CLIENT_FORGOT_PASSWORD_ENDPOINT = "api/v1/user/password_forgot/"
        const val CLIENT_RESET_PASSWORD_ENDPOINT = "api/v1/user/password_reset/"
        const val CLIENT_CHANGE_PASSWORD_ENDPOINT = "api/v1/user/password_change/"
    }
}