package com.example.domain.models

data class UserRegisterRequest(
    val email: String,
    val password: String,
    val passwordConfirm: String
)