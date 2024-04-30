package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.UserRegisterRequest
import com.google.gson.annotations.SerializedName

data class UserRegisterRequestDto(
    val email: String,
    val password: String,
    @SerializedName("password_confirm")
    val passwordConfirm: String
) : DataMapper<UserRegisterRequest> {

    override fun toDomain() = UserRegisterRequest(
        email = email,
        password = password,
        passwordConfirm = passwordConfirm
    )
}
