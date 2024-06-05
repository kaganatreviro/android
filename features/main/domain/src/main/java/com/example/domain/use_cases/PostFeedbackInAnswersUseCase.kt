package com.example.domain.use_cases

import com.example.domain.models.OrderRequest
import com.example.domain.models.PostFeedback
import com.example.domain.models.PostFeedbackInAnswers
import com.example.domain.repositories.EstablishmentRepository
import com.example.domain.repositories.OrderRepository

class PostFeedbackInAnswersUseCase(
    private val repo: EstablishmentRepository
) {
    operator fun invoke(feedback: PostFeedbackInAnswers) = repo.postFeedbackInAnswers(feedback)
}