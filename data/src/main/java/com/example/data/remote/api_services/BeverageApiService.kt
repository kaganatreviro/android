package com.example.data.remote.api_services

import com.example.data.remote.dto.BeverageDto
import retrofit2.http.GET
import retrofit2.http.Header

interface BeverageApiService {

    @GET(GET_BEVERAGES)
    suspend fun getBeverages(): List<BeverageDto>

    companion object {
        const val GET_BEVERAGES = "api/v1/beverage/beverages/"
    }
}