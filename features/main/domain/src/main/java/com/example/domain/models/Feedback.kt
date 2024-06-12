package com.example.domain.models

import java.io.Serializable

data class Feedback(
    val id: Int,
    val user: String,
    val displayUser: String,
    val createdAt: String,
    val text: String,
    val answers: Int,
    val userRole: String
): Serializable

data class PostFeedback(
    val establishment: Int,
    val text: String
)

data class PostFeedbackInAnswers(
    val feedback: Int,
    val text: String
)
