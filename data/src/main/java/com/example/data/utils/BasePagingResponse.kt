package com.example.data.utils

data class BasePagingResponse<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)