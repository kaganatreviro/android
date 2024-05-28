package com.example.data.repositories

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.either.Either
import com.example.data.remote.api_services.BeverageApiService
import com.example.data.remote.paging_src.BeveragePagingSource
import com.example.domain.models.Beverage
import com.example.domain.repositories.BeverageRepository
import kotlinx.coroutines.flow.Flow

class BeverageRepositoryImpl(
    private val apiService: BeverageApiService
) : BeverageRepository {

    override fun getBeverages(
        search: String?,
        availabilityStatus: String?
    ): Flow<PagingData<Beverage>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
            )
        ) {
            BeveragePagingSource(apiService, search, availabilityStatus)
        }.flow
    }

    override fun getBeverageById(id: Int): Flow<Either<String, Beverage>> = makeNetworkRequest {
        apiService.getBeverageById(id).toDomain()
    }
}