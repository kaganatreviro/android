package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.BuySubscriptionResponse
import com.google.gson.annotations.SerializedName

data class BuySubscriptionResponseDto(
    @SerializedName("approval_url")
    val approvalUrl: String
): DataMapper<BuySubscriptionResponse>{
    override fun toDomain() = BuySubscriptionResponse(
        approvalUrl = approvalUrl
    )
}