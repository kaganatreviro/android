package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.Feedback
import com.google.gson.annotations.SerializedName

data class FeedbackListDto(
    val id: Int,
    val user: String,
    @SerializedName("display_user")
    val displayUser: String,
    @SerializedName("created_at")
    val createdAt: String,
    val text: String,
    val answers: Int,
    @SerializedName("user_role")
    val userRole: String
) : DataMapper<Feedback> {
    override fun toDomain() = Feedback(
        id = id,
        user = user,
        displayUser = displayUser,
        createdAt = createdAt,
        text = text,
        answers = answers,
        userRole = userRole
    )
}