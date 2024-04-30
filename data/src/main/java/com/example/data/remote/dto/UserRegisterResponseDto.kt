package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.UserRegisterResponse

data class UserRegisterResponseDto(
    val id: Int,
    val email: String,
    val tokens: TokensDto
) : DataMapper<UserRegisterResponse> {

    override fun toDomain() = UserRegisterResponse(
        id = id,
        email = email,
        tokens = tokens.toDomain()
    )
}
