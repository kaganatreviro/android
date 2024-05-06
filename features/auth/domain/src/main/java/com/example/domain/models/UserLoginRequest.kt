package com.example.domain.models

data class UserLoginRequest(
    val email: String,
    val password: String
)