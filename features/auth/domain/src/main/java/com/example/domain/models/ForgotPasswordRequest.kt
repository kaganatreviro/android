package com.example.domain.models

import java.io.Serializable

data class ForgotPasswordRequest(
    val email: String
) : Serializable
