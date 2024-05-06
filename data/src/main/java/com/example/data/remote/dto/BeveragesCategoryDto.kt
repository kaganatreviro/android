package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.BeverageCategory

data class BeveragesCategoryDto(
    val id: Int,
    val name: String,
    val beverages: List<String>
): DataMapper<BeverageCategory>{
    override fun toDomain() = BeverageCategory (
        id = id,
        name = name,
        beverages = beverages
    )
}
