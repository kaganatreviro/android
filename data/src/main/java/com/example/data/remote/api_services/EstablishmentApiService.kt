package com.example.data.remote.api_services

import com.example.data.remote.dto.EstablishmentDetailsDto
import com.example.data.remote.dto.EstablishmentListDto
import retrofit2.http.GET
import retrofit2.http.Header

interface EstablishmentApiService {
    @GET(GET_ESTABLISHMENTS_LIST)
    suspend fun getEstablishmentList(
        @Header("Authorization") token: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzE0NTkzNDU5LCJpYXQiOjE3MTQ1ODI2NTksImp0aSI6IjAxODQzNWUwZDFlOTQ3YmNiNGE0Mzk4ODg4ZmVlOWNjIiwidXNlcl9pZCI6MTcsImVtYWlsIjoidXNlcjJAbWFpbC5ydSJ9.oaJyOPPlmSRxEFLrJJ8w8iM_nng-ZZTP9EpdtoVUjFQ"
    ): EstablishmentListDto

    @GET(GET_ESTABLISHMENT_BY_ID)
    suspend fun getEstablishmentById(
        @Header("Authorization") token: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzE0NTkzNDU5LCJpYXQiOjE3MTQ1ODI2NTksImp0aSI6IjAxODQzNWUwZDFlOTQ3YmNiNGE0Mzk4ODg4ZmVlOWNjIiwidXNlcl9pZCI6MTcsImVtYWlsIjoidXNlcjJAbWFpbC5ydSJ9.oaJyOPPlmSRxEFLrJJ8w8iM_nng-ZZTP9EpdtoVUjFQ"
    ): EstablishmentDetailsDto

    companion object {
        const val GET_ESTABLISHMENTS_LIST = "api/v1/partner/establishment/list/"
        const val GET_ESTABLISHMENT_BY_ID = "api/v1/partner/establishment/{id}/"
    }
}