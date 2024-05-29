package com.example.presentation.ui.fragments.establishment.menu

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.Menu
import com.example.domain.models.OrderRequest
import com.example.domain.models.OrderResponse
import com.example.domain.use_cases.GetEstablishmentDetailsByIdUseCase
import com.example.domain.use_cases.GetEstablishmentMenuByIdUseCase
import com.example.domain.use_cases.MakeOrderUseCase
import kotlinx.coroutines.flow.asStateFlow

class MenuViewModel(
private val getEstablishmentMenuUseCase: GetEstablishmentMenuByIdUseCase,
private val makeOrderUseCase: MakeOrderUseCase
) : BaseViewModel() {

    private val _establishmentMenuState = mutableUiStateFlow<List<Menu>>()
    val establishmentMenuState = _establishmentMenuState.asStateFlow()

    private val _orderState = mutableUiStateFlow<OrderResponse>()
    val orderState = _orderState.asStateFlow()

    fun getEstablishmentMenuById(id: Int) {
        getEstablishmentMenuUseCase(id).gatherRequest(_establishmentMenuState)
    }

    fun makeOrder(beverage: OrderRequest){
        makeOrderUseCase(beverage).gatherRequest(_orderState)
    }
}