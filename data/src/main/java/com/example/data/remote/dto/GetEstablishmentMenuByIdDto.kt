package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.MenuRequest

data class GetEstablishmentMenuByIdDto (
    var id: Int
): DataMapper<MenuRequest> {
    override fun toDomain() = MenuRequest (
        id = id
    )
}

fun MenuRequest.toDto() = GetEstablishmentMenuByIdDto(
    id = id
)