package com.example.domain.models.establishment

import com.example.domain.models.qrcode.QRCodeResponse
import java.io.Serializable

data class EstablishmentResponse(
    val id: Int,
    val name: String,
    val location: String,
    val description: String,
    val phoneNumber: String,
    val logo: String,
    val address: String,
    val happyhoursStart: String,
    val happyhoursEnd: String,
    val owner: Int,
    val qrCode: QRCodeResponse
):Serializable


