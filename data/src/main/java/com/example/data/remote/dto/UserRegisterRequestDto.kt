package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.UserRegisterRequest
import com.google.gson.annotations.SerializedName

data class UserRegisterRequestDto(
    val name: String,
    val email: String,
    @SerializedName("date_of_birth")
    val datOfBirth: String,
    val password: String,
    @SerializedName("password_confirm")
    val passwordConfirm: String
) : DataMapper<UserRegisterRequest> {

    override fun toDomain() = UserRegisterRequest(
        name = name,
        email = email,
        datOfBirth = datOfBirth,
        password = password,
        passwordConfirm = passwordConfirm
    )
}

fun UserRegisterRequest.toDto() = UserRegisterRequestDto(
    name = name,
    email = email,
    datOfBirth = datOfBirth,
    password = password,
    passwordConfirm = passwordConfirm
)
