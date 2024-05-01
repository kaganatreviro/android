package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.establishment.EstablishmentResponse
import com.example.domain.models.qrcode.QRCodeResponse
import com.google.gson.annotations.SerializedName

data class EstablishmentResponseDto(
    val id: Int,
    val name: String,
    val location: String,
    val description: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val logo: String,
    val address: String,
    @SerializedName("happyhours_start")
    val happyhoursStart: String,
    @SerializedName("happyhours_end")
    val happyhoursEnd: String,
    val owner: Int,
    @SerializedName("qr_code")
    val qrCode: QRCodeResponse
): DataMapper<EstablishmentResponse>{
    override fun toDomain() = EstablishmentResponse(
        id = id,
        name = name,
        location = location,
        description = description,
        phoneNumber = phoneNumber,
        logo = logo,
        address = address,
        happyhoursStart = happyhoursStart,
        happyhoursEnd = happyhoursEnd,
        owner = owner,
        qrCode = QRCodeResponse(
            id = id,
            qrCode = qrCode
        )
    )
}
