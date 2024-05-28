package com.example.data.remote.api_services

import com.example.data.remote.dto.OrderDto
import com.example.domain.models.OrderRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface OrderApiService {
    @GET(GET_ORDER_HISTORY)
    suspend fun getOrderHistory(): List<OrderDto>

    @Headers("Content-Type: application/json")
    @POST(MAKE_ORDER)
    suspend fun makeOrder(@Body beverage: OrderRequest): String

    companion object {
        const val GET_ORDER_HISTORY = "api/v1/order/client-order-history/"
        const val MAKE_ORDER = "api/v1/order/place-order/"
    }
}