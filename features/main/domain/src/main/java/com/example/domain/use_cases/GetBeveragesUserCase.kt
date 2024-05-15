package com.example.domain.use_cases

import com.example.domain.repositories.BeverageRepository

class GetBeveragesUserCase(
    private val repository: BeverageRepository
) {
    operator fun invoke(search: String?) = repository.getBeverages(search)
}