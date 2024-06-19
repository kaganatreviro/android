package com.example.data.remote.api_services

import com.example.data.remote.dto.BuySubscriptionRequestDto
import com.example.data.remote.dto.BuySubscriptionResponseDto
import com.example.data.remote.dto.SubscriptionsDto
import com.example.data.remote.dto.SubscriptionsPlanDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SubscriptionApiService {

    @GET(CHECK_SUBSCRIPTION_ACTIVE)
    suspend fun checkSubscriptionStatus(): SubscriptionsDto

    @GET(GET_SUBSCRIPTION_PLAN)
    suspend fun getSubscriptionPlan(): List<SubscriptionsPlanDto>

    @POST(BUY_SUBSCRIPTION)
    suspend fun buySubscription(@Path("plan_id") id: Int): BuySubscriptionResponseDto

    @POST(GET_FREE_TRIAL_PLAN)
    suspend fun getFreeTrialPlan(@Path("plan_id") id: Int): String

    companion object {
        const val CHECK_SUBSCRIPTION_ACTIVE = "api/v1/subscription/subscriptions/"
        const val GET_SUBSCRIPTION_PLAN = "api/v1/subscription/subscription-plans/"
        const val BUY_SUBSCRIPTION = "api/v1/subscription/create-payment/{plan_id}/"
        const val GET_FREE_TRIAL_PLAN = "api/v1/subscription/free-trial/"
    }
}