package com.example.domain.models

import java.io.Serializable

data class ResetPasswordRequest(
    val email: String,
    val reset_code: String
): Serializable
