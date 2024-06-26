package com.example.domain.models

import java.io.Serializable

data class Establishment(
    val id: Int,
    val name: String,
    val location: Location,
    val description: String,
    val phoneNumber: String?,
    val email: String?,
    val logo: String,
    val address: String?,
    val happyHoursStart: String,
    val happyHoursEnd: String,
    val feedbackCount: String
) :Serializable



