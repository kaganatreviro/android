package com.example.domain.models

import java.io.Serializable

data class ResetPasswordRequest(
    val email: String,
    val resetCode: String
): Serializable
