package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.ForgotPasswordRequest

data class UserForgotPasswordRequestDto(
    val email: String,
) : DataMapper<ForgotPasswordRequest> {

    override fun toDomain() = ForgotPasswordRequest(
        email = email
    )
}

fun ForgotPasswordRequest.toDto() = UserForgotPasswordRequestDto(
    email = email
)
