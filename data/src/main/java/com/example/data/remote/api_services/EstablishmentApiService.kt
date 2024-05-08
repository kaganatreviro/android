package com.example.data.remote.api_services

import com.example.data.remote.dto.EstablishmentDetailsDto
import com.example.data.remote.dto.EstablishmentListDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface EstablishmentApiService {
    @GET(GET_ESTABLISHMENTS_LIST)
    suspend fun getEstablishmentList(): EstablishmentListDto

    @GET(GET_ESTABLISHMENT_BY_ID)
    suspend fun getEstablishmentById(): EstablishmentDetailsDto

    companion object {
        const val GET_ESTABLISHMENTS_LIST = "api/v1/partner/establishment/list/"
        const val GET_ESTABLISHMENT_BY_ID = "api/v1/partner/establishment/{id}/"
    }
}