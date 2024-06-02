package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.OrderRequest
import com.example.domain.models.PostFeedback

data class PostFeedbackDto(
    val establishment: Int,
    val text: String
): DataMapper<PostFeedback>{
    override fun toDomain() = PostFeedback(
        establishment = establishment,
        text = text
    )
}

fun PostFeedback.toDto() = PostFeedbackDto(
    establishment = establishment,
    text = text
)