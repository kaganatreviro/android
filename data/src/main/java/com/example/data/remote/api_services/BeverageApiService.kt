package com.example.data.remote.api_services

import com.example.data.remote.dto.BeverageDto
import com.example.data.utils.BasePagingResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface BeverageApiService {

    @GET(GET_BEVERAGES)
    suspend fun getBeverages(
        @Query("search") search: String?,
        @Query("availability_status") availabilityStatus: Boolean?,
        @Query("limit") limit: String?,
        @Query("offset") offset: String?
    ): BasePagingResponse<BeverageDto>

    @GET(GET_BEVERAGE_BY_ID)
    suspend fun getBeverageById(
        @Path("id") id: Int
    ): BeverageDto

    companion object {
        const val GET_BEVERAGES = "api/v1/beverage/beverages/"
        const val GET_BEVERAGE_BY_ID = "api/v1/beverage/beverages/{id}/"
    }
}