package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.ChangePasswordRequest
import com.google.gson.annotations.SerializedName

data class UserChangePasswordRequestDto(
    val email: String,
    val password: String,
    @SerializedName("password_confirm")
    val passwordConfirm: String
) : DataMapper<ChangePasswordRequest> {

    override fun toDomain() = ChangePasswordRequest(
        email = email,
        password = password,
        passwordConfirm = passwordConfirm
    )
}

fun ChangePasswordRequest.toDto() = UserChangePasswordRequestDto(
    email = email,
    password = password,
    passwordConfirm = passwordConfirm
)
