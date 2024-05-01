package com.example.domain.models.beverage

import java.io.Serializable

data class BeverageResponse (
    val id: Int,
    val name: String,
    val price: String,
    val description: String,
    val availabilityStatus: Boolean,
    val establishment: Int,
    val category: Int
):Serializable