package com.example.domain.models

data class OrderRequest(
    val beverage: Int
)

data class Order(
    val id: Int,
    val orderDate: String,
    val establishmentName: String,
    val beverageName: String,
    val client: Int,
    val status: String
)
