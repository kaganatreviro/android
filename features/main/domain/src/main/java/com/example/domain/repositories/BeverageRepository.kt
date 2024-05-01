package com.example.domain.repositories

import com.example.core.either.Either
import com.example.domain.models.Beverage
import kotlinx.coroutines.flow.Flow

interface BeverageRepository {
    
    fun getBeverages(): Flow<Either<String, List<Beverage>>>
}