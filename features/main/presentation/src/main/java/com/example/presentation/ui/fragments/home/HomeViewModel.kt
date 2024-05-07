package com.example.presentation.ui.fragments.home

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.EstablishmentList
import com.example.domain.use_cases.GetEstablishmentListUseCase
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val getEstablishmentUseCase: GetEstablishmentListUseCase): BaseViewModel() {

    private val _establishmentListState = mutableUiStateFlow<EstablishmentList>()
    val establishmentListState = _establishmentListState.asStateFlow()

    init {
        getEstablishmentUseCase.invoke().gatherRequest(_establishmentListState)
    }
}