package com.example.domain.models

data class Beverage(
    val id: Int,
    val name: String,
    val price: String,
    val description: String,
    val availabilityStatus: Boolean,
    val establishment: String,
    val category: String,
)