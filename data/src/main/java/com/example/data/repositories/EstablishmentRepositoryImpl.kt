package com.example.data.repositories

import com.example.core.either.Either
import com.example.data.remote.api_services.EstablishmentApiService
import com.example.data.remote.dto.GetEstablishmentMenuByIdDto
import com.example.domain.models.EstablishmentDetails
import com.example.domain.models.Menu
import com.example.domain.repositories.EstablishmentRepository
import kotlinx.coroutines.flow.Flow

class EstablishmentRepositoryImpl(
    private val apiService: EstablishmentApiService
): EstablishmentRepository {
    override  fun getEstablishmentList(): Flow<Either<String, List<EstablishmentDetails>>> = makeNetworkRequest {
        apiService.getEstablishmentList().map { it.toDomain() }
    }

    override fun getEstablishmentMenuById(id: Int): Flow<Either<String, List<Menu>>> = makeNetworkRequest {
        apiService.getEstablishmentMenuById(id).map { it.toDomain() }
    }
}