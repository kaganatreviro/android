package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    val id: Int,
    val email: String,
    val name: String,
    @SerializedName("date_of_birth")
    val dateOfBirth: String?,
    val avatar: String?
) : DataMapper<User> {

    override fun toDomain() = User(
        id = id,
        email = email,
        name = name,
        dateOfBirth = dateOfBirth,
        avatar = avatar
    )
}