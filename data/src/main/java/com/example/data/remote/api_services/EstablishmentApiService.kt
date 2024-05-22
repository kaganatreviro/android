package com.example.data.remote.api_services

import com.example.data.remote.dto.EstablishmentDetailsDto
import com.example.data.remote.dto.MenuDto
import retrofit2.http.GET
import retrofit2.http.Path

interface EstablishmentApiService {
    @GET(GET_ESTABLISHMENTS_LIST)
    suspend fun getEstablishmentList(): List<EstablishmentDetailsDto>

    @GET(GET_ESTABLISHMENT_BY_ID)
    suspend fun getEstablishmentDetailsById(@Path("id") id: Int): EstablishmentDetailsDto

    @GET(GET_ESTABLISHMENT_MENU_BY_ID)
    suspend fun getEstablishmentMenuById(@Path("id") id: Int): List<MenuDto>

    companion object {
        const val GET_ESTABLISHMENTS_LIST = "api/v1/partner/establishments/"
        const val GET_ESTABLISHMENT_BY_ID = "api/v1/partner/establishments/{id}/"
        const val GET_ESTABLISHMENT_MENU_BY_ID = "api/v1/partner/menu/{id}/"
    }
}