package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.ResetPasswordRequest
import com.google.gson.annotations.SerializedName

data class UserResetPasswordRequestDto(
    val email: String,
    @SerializedName("reset_code")
    val resetCode: String
) : DataMapper<ResetPasswordRequest> {

    override fun toDomain() = ResetPasswordRequest(
        email = email,
        reset_code = resetCode
    )
}

fun ResetPasswordRequest.toDto() = UserResetPasswordRequestDto(
    email = email,
    resetCode = reset_code
)
