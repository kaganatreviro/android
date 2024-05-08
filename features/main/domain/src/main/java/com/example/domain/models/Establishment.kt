package com.example.domain.models

import java.io.Serializable

data class EstablishmentDetails(
    val id: Int,
    val name: String,
    val location: Location,
    val description: String,
    val phoneNumber: String,
    val logo: String,
    val address: String,
    val happyHoursStart: String,
    val happyHoursEnd: String,
    val owner: Int,
):Serializable

data class Location(
    val type: String,
    val coordinates: List<String>
)

data class EstablishmentList(
    val count: String,
    val next: String,
    val results: List<EstablishmentDetails>
):Serializable

data class Results(
    val id: Int,
    val name: String,
    val location: Location
):Serializable


