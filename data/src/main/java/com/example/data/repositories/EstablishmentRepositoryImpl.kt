package com.example.data.repositories

import com.example.core.either.Either
import com.example.data.remote.api_services.EstablishmentApiService
import com.example.domain.models.EstablishmentDetails
import com.example.domain.models.EstablishmentList
import com.example.domain.repositories.EstablishmentRepository
import kotlinx.coroutines.flow.Flow

class EstablishmentRepositoryImpl(
    private val apiService: EstablishmentApiService
): EstablishmentRepository {
    override suspend fun getEstablishmentList(): Flow<Either<String, EstablishmentList>> = makeNetworkRequest {
        apiService.getEstablishmentList().toDomain()
    }

    override fun getEstablishmentById(): Flow<Either<String, EstablishmentDetails>> = makeNetworkRequest {
        apiService.getEstablishmentById().toDomain()
    }
}