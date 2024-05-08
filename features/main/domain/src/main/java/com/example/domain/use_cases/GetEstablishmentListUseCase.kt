package com.example.domain.use_cases

import com.example.domain.repositories.EstablishmentRepository

class GetEstablishmentListUseCase(
    private val repo: EstablishmentRepository
) {
    operator fun invoke() = repo.getEstablishmentList()
}