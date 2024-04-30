package com.example.domain.models

data class UserLoginResponse(
    val refresh: String,
    val access: String
)