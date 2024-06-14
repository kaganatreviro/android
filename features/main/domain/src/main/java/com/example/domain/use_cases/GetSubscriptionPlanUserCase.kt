package com.example.domain.use_cases

import com.example.domain.repositories.SubscriptionRepository

class GetSubscriptionPlanUserCase(
    private val repository: SubscriptionRepository
) {
    operator fun invoke() = repository.getSubscriptionPlan()
}