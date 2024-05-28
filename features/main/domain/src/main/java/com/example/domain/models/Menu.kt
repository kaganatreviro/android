package com.example.domain.models

import java.io.Serializable

data class Menu(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val availabilityStatus: Boolean,
    val category: String,
    val establishment: String
) : Serializable


data class MenuRequest(
    val id: Int
)
