package com.example.domain.repositories

import com.example.core.either.Either
import com.example.domain.models.Order
import com.example.domain.models.OrderRequest
import com.example.domain.models.OrderResponse
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
      fun makeOrder(beverage: OrderRequest): Flow<Either<String, OrderResponse>>
      fun getOrderHistory(): Flow<Either<String, List<Order>>>
}