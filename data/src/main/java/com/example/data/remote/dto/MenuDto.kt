package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.google.gson.annotations.SerializedName

data class MenuDto(
    val id: Int,
    val name: String,
    val location: String,
    val description: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val logo: String,
    val address: String,
    @SerializedName("happyhours_start")
    val happyHoursStart: String,
    @SerializedName("happyhours_end")
    val happyHoursEnd: String,
    val beverages: List<BeverageDto>
) : DataMapper<com.example.domain.models.Menu> {
    override fun toDomain() = com.example.domain.models.Menu(
        id = id,
        name = name,
        location = location,
        description = description,
        phoneNumber = phoneNumber,
        logo = logo,
        address = address,
        happyHoursStart = happyHoursStart,
        happyHoursEnd = happyHoursEnd,
        beverages = beverages.map { it.toDomain() }
    )
}
