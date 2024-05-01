package com.example.data.remote.api_services

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {
    //Establishment
    @GET("/api/v1/partner/establishment/list/")
    fun getEstablishmentList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    )

    @GET("/api/v1/partner/establishment/{id}/")
    fun getEstablishmentById(@Path("id") id: Int)

    @GET("/api/v1/partner/menu/{id}/")
    fun getMenuById(@Path("id") id: Int)

    //User
    @GET("/api/v1/user/{id}/")
    fun getUserInfo(@Path("id") id: Int)

}