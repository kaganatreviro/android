package com.example.domain.use_cases

import com.example.domain.models.OrderRequest
import com.example.domain.repositories.OrderRepository

class MakeOrderUseCase(
    private val repo: OrderRepository
) {
    operator fun invoke(beverage: OrderRequest) = repo.makeOrder(beverage)
}