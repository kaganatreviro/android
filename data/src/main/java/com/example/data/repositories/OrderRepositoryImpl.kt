package com.example.data.repositories

import com.example.core.either.Either
import com.example.data.remote.api_services.OrderApiService
import com.example.data.remote.dto.toDto
import com.example.domain.models.Order
import com.example.domain.models.OrderRequest
import com.example.domain.models.OrderResponse
import com.example.domain.repositories.OrderRepository
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl(
    private val apiService: OrderApiService
): OrderRepository {
    override fun makeOrder(beverage: OrderRequest): Flow<Either<String, OrderResponse>> = makeNetworkRequest{
        apiService.makeOrder(beverage.toDto()).toDomain()
    }

    override fun getOrderHistory(): Flow<Either<String, List<Order>>> = makeNetworkRequest {
        apiService.getOrderHistory().map { it.toDomain() }
    }
}