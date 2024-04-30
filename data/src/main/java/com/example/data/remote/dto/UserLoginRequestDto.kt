package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.UserLoginRequest

data class UserLoginRequestDto(
    val email: String,
    val password: String
) : DataMapper<UserLoginRequest> {

    override fun toDomain() = UserLoginRequest(
        email = email,
        password = password,
    )
}
