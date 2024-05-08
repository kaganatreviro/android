package com.example.domain.models

import java.io.Serializable

data class ChangePasswordRequest(
    val email: String,
    val password: String,
    val passwordConfirm: String
): Serializable
