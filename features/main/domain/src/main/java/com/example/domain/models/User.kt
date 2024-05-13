package com.example.domain.models

data class User(
    val id: Int,
    val email: String,
    val name: String,
    val dateOfBirth: String?,
    val avatar: String?
)