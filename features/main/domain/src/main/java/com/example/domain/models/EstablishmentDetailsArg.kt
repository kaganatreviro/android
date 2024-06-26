package com.example.domain.models

import java.io.Serializable

data class EstablishmentDetailsArg(
    val establishmentId: Int,
    val enabledButton: Boolean
): Serializable