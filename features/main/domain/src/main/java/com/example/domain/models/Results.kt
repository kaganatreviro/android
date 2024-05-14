package com.example.domain.models

import java.io.Serializable

data class Results(
    val id: Int,
    val name: String,
    val location: Location
): Serializable