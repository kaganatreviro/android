package com.example.domain.models.qrcode

import java.io.Serializable

data class QRCodeResponse(
    val id: Int,
    val qrCodeImage: String
):Serializable
