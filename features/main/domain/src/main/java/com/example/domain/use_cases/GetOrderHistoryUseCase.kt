package com.example.domain.use_cases

import com.example.domain.repositories.EstablishmentRepository
import com.example.domain.repositories.OrderRepository

class GetOrderHistoryUseCase(
    private val repo: OrderRepository
) {
    operator fun invoke() = repo.getOrderHistory()
}