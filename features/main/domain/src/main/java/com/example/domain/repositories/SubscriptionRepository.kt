package com.example.domain.repositories

import com.example.core.either.Either
import com.example.domain.models.BuySubscription
import com.example.domain.models.BuySubscriptionResponse
import com.example.domain.models.Plan
import com.example.domain.models.Subscription
import kotlinx.coroutines.flow.Flow

interface SubscriptionRepository {
    fun checkSubscriptionStatus(): Flow<Either<String, Subscription>>
    fun getSubscriptionPlan(): Flow<Either<String, List<Plan>>>
    fun buySubscription(planId: Int): Flow<Either<String, BuySubscriptionResponse>>
}