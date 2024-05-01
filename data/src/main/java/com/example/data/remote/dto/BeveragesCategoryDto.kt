package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.beverage.BeveragesCategory

data class BeveragesCategoryDto(
    val id: Int,
    val name: String,
    val beverages: List<String>
): DataMapper<BeveragesCategory>{
    override fun toDomain() = BeveragesCategory (
        id = id,
        name = name,
        beverages = beverages
    )
}
