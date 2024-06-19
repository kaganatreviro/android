package com.example.domain.use_cases

import com.example.domain.repositories.BeverageRepository
import com.example.domain.repositories.SubscriptionRepository

class CheckSubscriptionStatusUserCase(
    private val repository: SubscriptionRepository
) {
    operator fun invoke() = repository.checkSubscriptionStatus()
}