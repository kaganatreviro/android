package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.Plan
import com.example.domain.models.Subscription
import com.google.gson.annotations.SerializedName

data class SubscriptionsPlanDto(
    val id: Int,
    val name: String,
    val duration: String,
    val price: String,
    val description: String,
    @SerializedName("free_trial_days")
    val freeTrialDays: Int
) : DataMapper<Plan> {
    override fun toDomain() = Plan(
        id = id,
        name = name,
        duration = duration,
        price = price,
        description = description,
        freeTrialDays = freeTrialDays
    )
}