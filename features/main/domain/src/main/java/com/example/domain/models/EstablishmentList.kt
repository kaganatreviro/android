package com.example.domain.models

import java.io.Serializable

data class EstablishmentList(
    val count: String,
    val next: String,
    val results: List<EstablishmentDetails>
): Serializable