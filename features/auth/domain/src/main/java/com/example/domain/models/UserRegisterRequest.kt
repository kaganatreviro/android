package com.example.domain.models

data class UserRegisterRequest(
    val name: String,
    val email: String,
    val datOfBirth: String,
    val password: String,
    val passwordConfirm: String
)