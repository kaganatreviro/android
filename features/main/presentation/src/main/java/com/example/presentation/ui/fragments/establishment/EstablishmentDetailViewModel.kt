package com.example.presentation.ui.fragments.establishment

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.Establishment
import com.example.domain.use_cases.GetEstablishmentDetailsByIdUseCase
import kotlinx.coroutines.flow.asStateFlow

class EstablishmentDetailViewModel(
    private val getEstablishmentDetailsUseCase: GetEstablishmentDetailsByIdUseCase,
) : BaseViewModel() {

    private val _establishmentDetailsState = mutableUiStateFlow<Establishment>()
    val establishmentDetailsState = _establishmentDetailsState.asStateFlow()

    fun getEstablishmentDetailsById(id: Int) {
        getEstablishmentDetailsUseCase(id).gatherRequest(_establishmentDetailsState)
    }
}