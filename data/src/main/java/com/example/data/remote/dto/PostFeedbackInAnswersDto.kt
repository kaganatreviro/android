package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.models.OrderRequest
import com.example.domain.models.PostFeedback
import com.example.domain.models.PostFeedbackInAnswers

data class PostFeedbackInAnswersDto(
    val feedback: Int,
    val text: String
): DataMapper<PostFeedbackInAnswers>{
    override fun toDomain() = PostFeedbackInAnswers(
        feedback = feedback,
        text = text
    )
}

fun PostFeedbackInAnswers.toDto() = PostFeedbackInAnswersDto(
    feedback = feedback,
    text = text
)