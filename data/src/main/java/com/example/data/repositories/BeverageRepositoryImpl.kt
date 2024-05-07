package com.example.data.repositories

import com.example.core.either.Either
import com.example.data.remote.api_services.BeverageApiService
import com.example.domain.models.Beverage
import com.example.domain.repositories.BeverageRepository
import kotlinx.coroutines.flow.Flow

class BeverageRepositoryImpl(
    private val apiService: BeverageApiService
): BeverageRepository {

    override fun getBeverages(): Flow<Either<String, List<Beverage>>> = makeNetworkRequest {
        apiService.getBeverages().map { it.toDomain() }
    }
}