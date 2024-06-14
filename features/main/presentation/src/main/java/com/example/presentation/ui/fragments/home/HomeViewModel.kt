package com.example.presentation.ui.fragments.home

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.EstablishmentDetails
import com.example.domain.models.Subscription
import com.example.domain.use_cases.CheckSubscriptionStatusUserCase
import com.example.domain.use_cases.GetEstablishmentListUseCase
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val getEstablishmentUseCase: GetEstablishmentListUseCase,
    private val checkSubscriptionStatusUserCase: CheckSubscriptionStatusUserCase
) : BaseViewModel() {

    private val _establishmentListState = mutableUiStateFlow<List<EstablishmentDetails>>()
    val establishmentListState = _establishmentListState.asStateFlow()

    private val _checkSubscriptionStatusState = mutableUiStateFlow<Subscription>()
    val checkSubscriptionStatusState = _checkSubscriptionStatusState.asStateFlow()

    fun getEstablishmentList() {
        getEstablishmentUseCase(null).gatherRequest(_establishmentListState)
    }

    fun checkSubscriptionStatus(){
        checkSubscriptionStatusUserCase().gatherRequest(_checkSubscriptionStatusState)
    }
}