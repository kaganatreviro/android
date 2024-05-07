package com.example.domain.use_cases

import com.example.domain.repositories.EstablishmentRepository

class GetEstablishmentByIdUseCase(
    private val repo: EstablishmentRepository
) {
   suspend operator fun invoke() = repo.getEstablishmentById()
}