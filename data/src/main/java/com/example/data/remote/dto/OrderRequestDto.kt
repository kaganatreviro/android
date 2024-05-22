package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.OrderRequest

data class OrderRequestDto(
    val beverage: Int
): DataMapper<OrderRequest>{
    override fun toDomain() = OrderRequest(
        beverage = beverage
    )
}

fun OrderRequest.toDto() = OrderRequestDto(
    beverage = beverage
)