package com.example.domain.models

data class OrderRequest(
    val beverage: Int
)

data class Order(
    val id: Int,
    val orderDate: String,
    val establishmentName: String,
    val beverageName: String,
    val client: String,
    val status: String
)

data class OrderResponse(
    val id: Int,
    val client: Int,
    val beverage: Int,
    val establishment: Int,
    val orderDate: String
)
