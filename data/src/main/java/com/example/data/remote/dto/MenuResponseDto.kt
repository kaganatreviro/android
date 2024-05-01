package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.beverage.BeverageResponse
import com.example.domain.models.menu.MenuResponse

data class MenuResponseDto(
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
): DataMapper<MenuResponse>{
    override fun toDomain() = MenuResponse (
        id = id,
        name = name,
        location = location,
        description = description,
        phone_number = phone_number,
        logo = logo,
        address = address,
        happyhours_start = happyhours_start,
        happyhours_end = happyhours_end,
        beverages = beverages
    )
}
