package com.example.domain.repositories

import com.example.core.either.Either
import com.example.domain.models.EstablishmentDetails
import com.example.domain.models.EstablishmentList
import kotlinx.coroutines.flow.Flow

interface EstablishmentRepository {

      fun getEstablishmentList(): Flow<Either<String, EstablishmentList>>
      fun getEstablishmentById(): Flow<Either<String, EstablishmentDetails>>
}