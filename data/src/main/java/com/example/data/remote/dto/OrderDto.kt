package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.Order
import com.google.gson.annotations.SerializedName

data class OrderDto(
    val id: Int,
    @SerializedName("order_date")
    val orderDate: String,
    @SerializedName("establishment_name")
    val establishmentName: String,
    @SerializedName("beverage_name")
    val beverageName: String,
    val client: String,
    val status: String
) : DataMapper<Order> {
    override fun toDomain() = Order(
        id = id,
        orderDate = orderDate,
        establishmentName = establishmentName,
        beverageName = beverageName,
        client = client,
        status = status
    )
}