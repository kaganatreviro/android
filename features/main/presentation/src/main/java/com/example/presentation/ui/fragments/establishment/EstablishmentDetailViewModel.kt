package com.example.presentation.ui.fragments.establishment

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.EstablishmentDetails
import com.example.domain.models.Menu
import com.example.domain.models.OrderRequest
import com.example.domain.use_cases.GetEstablishmentDetailsByIdUseCase
import com.example.domain.use_cases.GetEstablishmentMenuByIdUseCase
import com.example.domain.use_cases.MakeOrderUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow

class EstablishmentDetailViewModel(
    private val getEstablishmentDetailsUseCase: GetEstablishmentDetailsByIdUseCase,
) : BaseViewModel() {

    private val _establishmentDetailsState = mutableUiStateFlow<EstablishmentDetails>()
    val establishmentDetailsState = _establishmentDetailsState.asStateFlow()

    fun getEstablishmentDetailsById(id: Int) {
        getEstablishmentDetailsUseCase(id).gatherRequest(_establishmentDetailsState)
    }
}