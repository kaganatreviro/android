package com.example.presentation.ui.fragments.profile.subscriptions

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.BuySubscription
import com.example.domain.models.Plan
import com.example.domain.use_cases.BuySubscriptionPlanByIdUserCase
import com.example.domain.use_cases.GetSubscriptionPlanUserCase
import kotlinx.coroutines.flow.asStateFlow

class SubscriptionsViewModel(
    private val getSubscriptionPlanUserCase: GetSubscriptionPlanUserCase,
    private val buySubscriptionPlanByIdUserCase: BuySubscriptionPlanByIdUserCase
): BaseViewModel() {

    private val _getSubscriptionPlanState = mutableUiStateFlow<List<Plan>>()
    val getSubscriptionPlanState = _getSubscriptionPlanState.asStateFlow()
    private val _buySubscriptionPlanByIdState = mutableUiStateFlow<String>()
    val buySubscriptionPlanByIdState = _buySubscriptionPlanByIdState.asStateFlow()

    fun getSubscriptionPlan(){
        getSubscriptionPlanUserCase().gatherRequest(_getSubscriptionPlanState)
    }

    fun buySubscriptionPlanById(id: BuySubscription){
        buySubscriptionPlanByIdUserCase(id).gatherRequest(_buySubscriptionPlanByIdState)
    }
}