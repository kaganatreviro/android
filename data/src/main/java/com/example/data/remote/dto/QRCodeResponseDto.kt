package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.qrcode.QRCodeResponse
import com.google.gson.annotations.SerializedName

data class QRCodeResponseDto (
    val id: Int,
    @SerializedName("qr_code_image")
    val qrCodeImage: String
): DataMapper<QRCodeResponse> {
    override fun toDomain() = QRCodeResponse(
        id = id,
        qrCodeImage = qrCodeImage
    )
}
