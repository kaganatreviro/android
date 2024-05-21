package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.OrderRequest
import com.google.gson.annotations.SerializedName

data class OrderDto(
    val id: Int,
    @SerializedName("order_date")
    val orderDate: String,
    @SerializedName("establishment_name")
    val establishmentName: String,
    @SerializedName("beverage_name")
    val beverageName: String,
    val client: Int,
    val status: String
) : DataMapper<com.example.domain.models.Order> {
    override fun toDomain() = com.example.domain.models.Order(
        id = id,
        orderDate = orderDate,
        establishmentName = establishmentName,
        beverageName = beverageName,
        client = client,
        status = status
    )
}

data class OrderRequestDto(
    val beverage: Int
): DataMapper<OrderRequest>{
    override fun toDomain() = OrderRequest(
        beverage = beverage
    )
}