package com.example.presentation.ui.fragments.profile.subscriptions

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.BuySubscriptionResponse
import com.example.domain.models.Plan
import com.example.domain.use_cases.BuySubscriptionPlanByIdUserCase
import com.example.domain.use_cases.GetFreeTrialPlanUserCase
import com.example.domain.use_cases.GetSubscriptionPlanUserCase
import kotlinx.coroutines.flow.asStateFlow

class SubscriptionsViewModel(
    private val getSubscriptionPlanUserCase: GetSubscriptionPlanUserCase,
    private val buySubscriptionPlanByIdUserCase: BuySubscriptionPlanByIdUserCase,
    private val getFreeTrialPlanUserCase: GetFreeTrialPlanUserCase
): BaseViewModel() {

    private val _getFreeTrialPlanState = mutableUiStateFlow<String>()
    val getFreeTrialPlanState = _getFreeTrialPlanState.asStateFlow()

    private val _getSubscriptionPlanState = mutableUiStateFlow<List<Plan>>()
    val getSubscriptionPlanState = _getSubscriptionPlanState.asStateFlow()


    private val _buySubscriptionPlanByIdState = mutableUiStateFlow<BuySubscriptionResponse>()
    val buySubscriptionPlanByIdState = _buySubscriptionPlanByIdState.asStateFlow()

    fun getSubscriptionPlan(){
        getSubscriptionPlanUserCase().gatherRequest(_getSubscriptionPlanState)
    }

    fun buySubscriptionPlanById(id: Int){
        buySubscriptionPlanByIdUserCase(id).gatherRequest(_buySubscriptionPlanByIdState)
    }

    fun getFreeTrialPlan(id: Int){
        getFreeTrialPlanUserCase(id).gatherRequest(_getFreeTrialPlanState)
    }
}