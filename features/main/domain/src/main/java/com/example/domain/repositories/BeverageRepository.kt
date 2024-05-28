package com.example.domain.repositories

import androidx.paging.PagingData
import com.example.core.either.Either
import com.example.domain.models.Beverage
import kotlinx.coroutines.flow.Flow

interface BeverageRepository {

    fun getBeverages(search: String?, availabilityStatus: String?): Flow<PagingData<Beverage>>
    fun getBeverageById(id: Int): Flow<Either<String, Beverage>>
}