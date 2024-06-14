package com.example.core.either

data class AuthenticationError(
    val message: String,
    val code: Int
)