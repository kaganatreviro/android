package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.BuySubscriptionResponse
import com.example.domain.models.FreeTrialPlanResponse
import com.google.gson.annotations.SerializedName

data class FreeTrialPlanResponseDto(
    val status: String,
    @SerializedName("subscription_id")
    val subscriptionId : Int,
    @SerializedName("end_date")
    val endDate: String
): DataMapper<FreeTrialPlanResponse>{
    override fun toDomain() = FreeTrialPlanResponse(
        status = status,
        subscriptionId = subscriptionId,
        endDate = endDate
    )
}