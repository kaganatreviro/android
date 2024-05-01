package com.example.domain.models.beverage

import java.io.Serializable

data class BeveragesCategory(
    val id: Int,
    val name: String,
    val beverages: List<String>
):Serializable