package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.Establishment
import com.example.domain.models.Location
import com.google.gson.annotations.SerializedName

data class EstablishmentDto(
    val id: Int,
    val name: String,
    val location: Location,
    val description: String,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    val email: String?,
    val logo: String,
    val address: String?,
    @SerializedName("happyhours_start")
    val happyHoursStart: String,
    @SerializedName("happyhours_end")
    val happyHoursEnd: String,
    @SerializedName("feedback_count")
    val feedbackCount: String
) : DataMapper<Establishment> {
    override fun toDomain() = Establishment(
        id = id,
        name = name,
        location = location,
        description = description,
        phoneNumber = phoneNumber,
        email = email,
        logo = logo,
        address = address,
        happyHoursStart = happyHoursStart,
        happyHoursEnd = happyHoursEnd,
        feedbackCount = feedbackCount
    )
}