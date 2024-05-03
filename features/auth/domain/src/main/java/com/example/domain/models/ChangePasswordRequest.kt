package com.example.domain.models

data class ChangePasswordRequest(
    val email: String,
    val password: String,
    val passwordConfirm: String
)
