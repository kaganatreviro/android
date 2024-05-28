package com.example.domain.repositories

import com.example.core.either.Either
import com.example.domain.models.Order
import com.example.domain.models.OrderRequest
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
      fun makeOrder(beverage: OrderRequest): Flow<Either<String, String>>
      fun getOrderHistory(): Flow<Either<String, List<Order>>>
}