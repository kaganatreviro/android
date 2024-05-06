package com.example.data.remote.api_services

import com.example.data.remote.dto.BeverageDto
import retrofit2.http.GET
import retrofit2.http.Header

interface BeverageApiService {

    @GET(GET_BEVERAGES)
    suspend fun getBeverages(
        @Header("Authorization") token: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzE0NTkzNDU5LCJpYXQiOjE3MTQ1ODI2NTksImp0aSI6IjAxODQzNWUwZDFlOTQ3YmNiNGE0Mzk4ODg4ZmVlOWNjIiwidXNlcl9pZCI6MTcsImVtYWlsIjoidXNlcjJAbWFpbC5ydSJ9.oaJyOPPlmSRxEFLrJJ8w8iM_nng-ZZTP9EpdtoVUjFQ"
    ): List<BeverageDto>

    companion object {
        const val GET_BEVERAGES = "api/v1/beverage/beverages/"
    }
}