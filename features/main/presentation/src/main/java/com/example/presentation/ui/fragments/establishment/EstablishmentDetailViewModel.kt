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
    private val getEstablishmentMenuUseCase: GetEstablishmentMenuByIdUseCase,
    private val getEstablishmentDetailsUseCase: GetEstablishmentDetailsByIdUseCase,
    private val makeOrderUseCase: MakeOrderUseCase
) : BaseViewModel() {

    private val _establishmentMenuState = mutableUiStateFlow<List<Menu>>()
    val establishmentMenuState = _establishmentMenuState.asStateFlow()

    private val _establishmentDetailsState = mutableUiStateFlow<EstablishmentDetails>()
    val establishmentDetailsState = _establishmentDetailsState.asStateFlow()

    private val _orderState = mutableUiStateFlow<String>()
    val orderState = _orderState.asStateFlow()

    suspend fun getEstablishmentDetailsById(id: Int) {
        getEstablishmentMenuUseCase(id).gatherRequest(_establishmentMenuState)
        delay(500)
        getEstablishmentDetailsUseCase(id).gatherRequest(_establishmentDetailsState)
    }

    fun makeOrder(beverage: OrderRequest){
        makeOrderUseCase(beverage).gatherRequest(_orderState)
    }
}