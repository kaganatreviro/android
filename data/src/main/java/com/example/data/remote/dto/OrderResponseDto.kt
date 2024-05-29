package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.OrderRequest
import com.example.domain.models.OrderResponse
import com.google.gson.annotations.SerializedName

data class OrderResponseDto(
    val id: Int,
    val client: Int,
    val beverage: Int,
    val establishment: Int,
    @SerializedName("order_date")
    val orderDate: String
): DataMapper<OrderResponse>{
    override fun toDomain() = OrderResponse(
        id, client, beverage, establishment, orderDate
    )
}