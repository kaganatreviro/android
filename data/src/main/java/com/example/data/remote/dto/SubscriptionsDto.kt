package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.Plan
import com.example.domain.models.Subscription
import com.google.gson.annotations.SerializedName

data class SubscriptionsDto(
    val id: Int,
    val user: String,
    val plan: Plan,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("is_active")
    val isActive : Boolean,
    @SerializedName("is_trial")
    val isTrial: Boolean
) : DataMapper<Subscription> {
    override fun toDomain() = Subscription(
        id = id,
        user = user,
        plan = plan,
        startDate = startDate,
        endDate = endDate,
        isActive = isActive,
        isTrial = isTrial
    )
}