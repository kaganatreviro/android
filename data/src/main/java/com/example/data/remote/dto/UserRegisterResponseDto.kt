package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.UserRegisterResponse
import com.google.gson.annotations.SerializedName

data class UserRegisterResponseDto(
    val id: Int,
    val email: String,
    val name: String,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    val avatar: String?,
    val tokens: TokensDto
) : DataMapper<UserRegisterResponse> {

    override fun toDomain() = UserRegisterResponse(
        id = id,
        email = email,
        name = name,
        dateOfBirth = dateOfBirth,
        avatar = avatar,
        tokens = tokens.toDomain()
    )
}