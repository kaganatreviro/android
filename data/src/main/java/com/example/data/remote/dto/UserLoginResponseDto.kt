package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.UserLoginResponse

data class UserLoginResponseDto(
    val refresh: String,
    val access: String
) : DataMapper<UserLoginResponse> {

    override fun toDomain() = UserLoginResponse(
        refresh = refresh,
        access = access
    )
}
