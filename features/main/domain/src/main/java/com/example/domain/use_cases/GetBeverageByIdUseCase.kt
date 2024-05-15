package com.example.domain.use_cases

import com.example.domain.repositories.BeverageRepository

class GetBeverageByIdUseCase(
    private val repository: BeverageRepository
) {
    operator fun invoke(id: Int) = repository.getBeverageById(id)
}