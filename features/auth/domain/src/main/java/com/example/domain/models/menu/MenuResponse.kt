package com.example.domain.models.menu

import com.example.domain.models.beverage.BeverageResponse
import java.io.Serializable

data class MenuResponse(
    val id: Int,
    val name: String,
    val location: String,
    val description: String,
    val phone_number: String,
    val logo: String,
    val address: String,
    val happyhours_start: String,
    val happyhours_end: String,
    val beverages: List<BeverageResponse>
):Serializable
