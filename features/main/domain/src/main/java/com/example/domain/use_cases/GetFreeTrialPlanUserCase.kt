package com.example.domain.use_cases

import com.example.domain.models.BuySubscription
import com.example.domain.models.Plan
import com.example.domain.repositories.BeverageRepository
import com.example.domain.repositories.SubscriptionRepository

class GetFreeTrialPlanUserCase(
    private val repository: SubscriptionRepository
) {
    operator fun invoke(planId: BuySubscription) = repository.getFreeTrialPlan(planId)
}