package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.Menu
import com.example.domain.models.MenuRequest
import com.example.domain.models.UserLoginRequest

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