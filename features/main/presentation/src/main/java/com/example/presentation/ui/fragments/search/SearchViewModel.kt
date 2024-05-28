package com.example.presentation.ui.fragments.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.Beverage
import com.example.domain.use_cases.GetBeveragesUserCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModel(
    private val getBeverageUserCase: GetBeveragesUserCase
) : BaseViewModel() {

    private val searchBy = MutableStateFlow<String?>("")
    private val filterByCategory = MutableStateFlow<String?>(null)

    init {
        getBeverages()
    }

    fun getBeverages(): Flow<PagingData<Beverage>> {
        return combine(searchBy, filterByCategory) { search, category ->
            Pair(search, category)
        }.flatMapLatest { (search, category) ->
            getBeverageUserCase(search, category)
        }.cachedIn(viewModelScope)
    }

    fun beverageSearch(text: String?) {
        if (text == searchBy.value) return
        searchBy.value = text
    }

    fun filterBeveragesByStatus(availabilityStatus: String) {
        if (availabilityStatus == filterByCategory.value) return
        filterByCategory.value = availabilityStatus
    }
}