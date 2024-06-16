package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.BuySubscription
import com.google.gson.annotations.SerializedName

data class BuySubscriptionRequestDto (
    @SerializedName("plan_id")
    val planId: Int
):DataMapper<BuySubscription>{
    override fun toDomain() = BuySubscription(
        planId = planId
    )
}

fun BuySubscription.toDto() = BuySubscriptionRequestDto(
    planId = planId
)
