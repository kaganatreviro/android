package com.example.presentation.ui.fragments.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.Beverage
import com.example.domain.models.EstablishmentDetails
import com.example.domain.use_cases.GetBeveragesUserCase
import com.example.domain.use_cases.GetEstablishmentListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModel(
    private val getBeverageUserCase: GetBeveragesUserCase,
    private val getEstablishmentListUseCase: GetEstablishmentListUseCase
) : BaseViewModel() {

    private val _establishmentsState = mutableNewUiStateFlow<List<EstablishmentDetails>>()
    val establishmentsState = _establishmentsState.asStateFlow()

    private val searchBy = MutableStateFlow<String?>("")
    private val filterByCategory = MutableStateFlow<String?>(null)

    init {
        getBeverages()
        getEstablishments()
    }

    private fun getEstablishments(query: String? = null) {
        getEstablishmentListUseCase(query).newGatherRequest(_establishmentsState)
    }

    fun getBeverages(): Flow<PagingData<Beverage>> {
        return combine(searchBy, filterByCategory) { search, category ->
            Pair(search, category)
        }.flatMapLatest { (search, category) ->
            getBeverageUserCase(search, category)
        }.cachedIn(viewModelScope)
    }

    fun searchBy(text: String?) {
        if (text == searchBy.value) return
        searchBy.value = text
        getEstablishments(text)
    }

    fun filterBeveragesByStatus(availabilityStatus: String) {
        if (availabilityStatus == filterByCategory.value) return
        filterByCategory.value = availabilityStatus
    }
}