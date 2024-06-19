package com.example.domain.models

import java.io.Serializable

data class Subscription(
    val id: Int,
    val user: String,
    val plan : Plan,
    val startDate: String,
    val endDate: String,
    val isActive : Boolean,
    val isTrial: Boolean
): Serializable

data class Plan(
    val id: Int,
    val name: String,
    val duration: String,
    val price: String,
    val description: String,
    val freeTrialDays: Int
): Serializable

data class BuySubscription(
    val planId: Int
)

data class FreeTrialPlanResponse(
    val status: String,
    val subscriptionId: Int,
    val endDate: String
)

data class BuySubscriptionResponse(
    val approvalUrl: String
):Serializable