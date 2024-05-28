package com.example.domain.repositories

import com.example.core.either.Either
import com.example.domain.models.EstablishmentDetails
import com.example.domain.models.Menu
import kotlinx.coroutines.flow.Flow

interface EstablishmentRepository {

      fun getEstablishmentList(): Flow<Either<String, List<EstablishmentDetails>>>
      fun getEstablishmentMenuById(id: Int): Flow<Either<String, List<Menu>>>
      fun getEstablishmentDetailsById(id: Int): Flow<Either<String, EstablishmentDetails>>
}