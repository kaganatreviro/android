package com.example.domain.models

data class UserRegisterResponse(
    val id: Int,
    val email: String,
    val name: String,
    val dateOfBirth: String,
    val avatar: String?,
    val tokens: Tokens
)