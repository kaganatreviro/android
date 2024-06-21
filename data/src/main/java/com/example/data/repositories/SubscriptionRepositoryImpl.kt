package com.example.data.repositories

import com.example.core.either.Either
import com.example.data.remote.api_services.SubscriptionApiService
import com.example.data.remote.dto.toDto
import com.example.domain.models.BuySubscription
import com.example.domain.models.BuySubscriptionResponse
import com.example.domain.models.FreeTrialPlanResponse
import com.example.domain.models.Plan
import com.example.domain.models.Subscription
import com.example.domain.repositories.SubscriptionRepository
import kotlinx.coroutines.flow.Flow

class SubscriptionRepositoryImpl(
    private val apiService: SubscriptionApiService,
) : SubscriptionRepository {
    override fun checkSubscriptionStatus(): Flow<Either<String, Subscription>> =
        makeNetworkRequest {
            apiService.checkSubscriptionStatus().toDomain()
        }

    override fun getSubscriptionPlan(): Flow<Either<String, List<Plan>>> = makeNetworkRequest {
        apiService.getSubscriptionPlan().map { it.toDomain() }
    }

    override fun buySubscription(planId: Int): Flow<Either<String, BuySubscriptionResponse>> =
        makeNetworkRequest {
            apiService.buySubscription(planId).toDomain()
        }

    override fun getFreeTrialPlan(planId: BuySubscription): Flow<Either<String, FreeTrialPlanResponse>> =
        makeNetworkRequest {
            apiService.getFreeTrialPlan(planId.toDto()).toDomain()
        }
}