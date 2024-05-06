package com.example.domain.use_cases

import com.example.domain.repositories.BeverageRepository

class GetBeverageUserCase(
    private val repository: BeverageRepository
) {
    operator fun invoke() = repository.getBeverages()
}