package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.Tokens

data class TokensDto(
    val refresh: String,
    val access: String
): DataMapper<Tokens> {

    override fun toDomain() = Tokens(
        refresh = refresh,
        access = access
    )
}