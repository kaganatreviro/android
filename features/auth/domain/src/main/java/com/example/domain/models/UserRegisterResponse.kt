package com.example.domain.models

data class UserRegisterResponse(
    val id: Int,
    val email: String,
    val tokens: Tokens
)