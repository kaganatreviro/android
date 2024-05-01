package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.Beverage
import com.google.gson.annotations.SerializedName

data class BeverageDto(
    val id: Int,
    val name: String,
    val price: String,
    val description: String,
    @SerializedName("availability_status")
    val availabilityStatus: Boolean,
    val establishment: String,
    val category: String,
) : DataMapper<Beverage> {

    override fun toDomain() = Beverage(
        id = id,
        name = name,
        price = price,
        description = description,
        availabilityStatus = availabilityStatus,
        establishment = establishment,
        category = category
    )
}
