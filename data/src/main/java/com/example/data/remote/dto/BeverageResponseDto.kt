package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.beverage.BeverageResponse
import com.google.gson.annotations.SerializedName


data class BeverageResponseDto (
    val id: Int,
    val name: String,
    val price: String,
    val description: String,
    @SerializedName("availability_status")
    val availabilityStatus: Boolean,
    val establishment: Int,
    val category: Int
): DataMapper<BeverageResponse> {

    override fun toDomain() = BeverageResponse(
        id = id,
        name = name,
        price = price,
        description = description,
        availabilityStatus = availabilityStatus,
        establishment = establishment,
        category = category
    )
}