package com.example.domain.use_cases

import com.example.domain.repositories.EstablishmentRepository

class GetEstablishmentFeedbackListUseCase(
    private val repo: EstablishmentRepository
) {
    operator fun invoke(int: Int) = repo.getEstablishmentFeedbackList(int)
}