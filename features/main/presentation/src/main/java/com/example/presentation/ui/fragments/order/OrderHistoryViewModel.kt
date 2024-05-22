package com.example.presentation.ui.fragments.order

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.Order
import com.example.domain.use_cases.GetOrderHistoryUseCase
import kotlinx.coroutines.flow.asStateFlow

class OrderHistoryViewModel(private val getOrderHistoryUseCase: GetOrderHistoryUseCase) :
    BaseViewModel() {

    private val order_historyState = mutableUiStateFlow<List<Order>>()
    val orderHistoryState = order_historyState.asStateFlow()

    fun getOrderHistory() {
        getOrderHistoryUseCase().gatherRequest(order_historyState)
    }
}