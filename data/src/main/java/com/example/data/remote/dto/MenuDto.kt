package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.Menu
import com.example.domain.models.UserLoginRequest
import com.example.domain.use_cases.GetEstablishmentMenuByIdUseCase
import com.google.gson.annotations.SerializedName

data class MenuDto(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    @SerializedName("availability_status")
    val availabilityStatus: Boolean,
    val category: String,
    val establishment: String
) : DataMapper<com.example.domain.models.Menu> {
    override fun toDomain() = com.example.domain.models.Menu(
        id = id,
        name = name,
        price = price,
        description = description,
        availabilityStatus = availabilityStatus,
        category = category,
        establishment = establishment
    )
}