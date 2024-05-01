package com.example.domain.models

data class Menu(
    val id: Int,
    val name: String,
    val location: String,
    val description: String,
    val phoneNumber: String,
    val logo: String,
    val address: String,
    val happyHoursStart: String,
    val happyHoursEnd: String,
    val beverages: List<Beverage>
)
