package com.example.presentation.ui.fragments.home

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.EstablishmentDetails
import com.example.domain.use_cases.GetEstablishmentListUseCase
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val getEstablishmentUseCase: GetEstablishmentListUseCase
) : BaseViewModel() {

    private val _establishmentListState = mutableNewUiStateFlow<List<EstablishmentDetails>>()
    val establishmentListState = _establishmentListState.asStateFlow()

     fun getEstablishmentList() {
        getEstablishmentUseCase(null).newGatherRequest(state = _establishmentListState)
    }
}