package com.example.data.remote.paging_src

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.api_services.BeverageApiService
import com.example.data.remote.dto.BeverageDto
import com.example.domain.models.Beverage

private const val START_PAGE_INDEX = 0
private const val PAGE_SIZE = 10

class BeveragePagingSource(
    private val beverageApiService: BeverageApiService,
    private val searchQuery: String?,
    private val availabilityStatus: String?
) : PagingSource<Int, Beverage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beverage> {
        return try {
            val beverageStatus = if (availabilityStatus == "Available") true else null
            val position = params.key ?: START_PAGE_INDEX
            val response = beverageApiService.getBeverages(
                searchQuery,
                beverageStatus,
                PAGE_SIZE.toString(),
                position.toString()
            )

            val nextOffset = response.next?.let {
                val uri = Uri.parse(it)
                uri.getQueryParameter("offset")?.toInt()
            }

            LoadResult.Page(
                data = response.results.map { it.toDomain() },
                prevKey = null,
                nextKey = nextOffset
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Beverage>): Int {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        } ?: START_PAGE_INDEX
    }
}