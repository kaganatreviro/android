package com.example.domain.models

data class ResetPasswordRequest(
    val email: String,
    val resetCode: String
)
