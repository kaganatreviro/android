package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.Establishment
import com.google.gson.annotations.SerializedName

data class EstablishmentDto(
    val id: Int,
    val name: String,
    val location: String,
    val description: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val logo: String,
    val address: String,
    @SerializedName("happyhours_start")
    val happyHoursStart: String,
    @SerializedName("happyhours_end")
    val happyHoursEnd: String,
    val owner: Int,
    @SerializedName("qr_code")
    val qrCode: QRCodeResponseDto
) : DataMapper<Establishment> {
    override fun toDomain() = Establishment(
        id = id,
        name = name,
        location = location,
        description = description,
        phoneNumber = phoneNumber,
        logo = logo,
        address = address,
        happyHoursStart = happyHoursStart,
        happyHoursEnd = happyHoursEnd,
        owner = owner,
        qrCode = qrCode.toDomain()
    )
}
