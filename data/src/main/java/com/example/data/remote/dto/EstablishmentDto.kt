package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.EstablishmentDetails
import com.example.domain.models.EstablishmentList
import com.example.domain.models.Location
import com.example.domain.models.Results
import com.example.domain.models.UserLoginRequest
import com.google.gson.annotations.SerializedName

data class EstablishmentListDto(
    val count: String,
    val next: String,
    val results: List<Results>,
    val description: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val logo: String,
    val address: String,
    @SerializedName("happyhours_start")
    val happyHoursStart: String,
    @SerializedName("happyhours_end")
    val happyHoursEnd: String,
    val owner: Int
) : DataMapper<EstablishmentList> {
    override fun toDomain() = EstablishmentList(
        count = count,
        next = next,
        results = results,
        description = description,
        phoneNumber = phoneNumber,
        logo = logo,
        address = address,
        happyHoursStart = happyHoursStart,
        happyHoursEnd = happyHoursEnd,
        owner = owner
    )
}

data class EstablishmentDetailsDto(
    val id: Int,
    val name: String,
    val location: Location,
    val description: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val logo: String,
    val address: String,
    @SerializedName("happyhours_start")
    val happyHoursStart: String,
    @SerializedName("happyhours_end")
    val happyHoursEnd: String,
    val owner: Int
) : DataMapper<EstablishmentDetails> {
    override fun toDomain() = EstablishmentDetails(
        id = id,
        name = name,
        location = location,
        description = description,
        phoneNumber = phoneNumber,
        logo = logo,
        address = address,
        happyHoursStart = happyHoursStart,
        happyHoursEnd = happyHoursEnd,
        owner = owner
    )
}