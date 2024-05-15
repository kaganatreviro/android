package com.example.presentation.ui.fragments.beveage

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.Beverage
import com.example.domain.use_cases.GetBeverageByIdUseCase
import kotlinx.coroutines.flow.asStateFlow

class BeverageDetailsViewModel(
    private val getBeverageByIdUseCase: GetBeverageByIdUseCase
): BaseViewModel() {

    private val _beverageState = mutableUiStateFlow<Beverage>()
    val beverageState = _beverageState.asStateFlow()

    fun getBeverageById(beverageId: Int) {
        getBeverageByIdUseCase(beverageId).gatherRequest(_beverageState)
    }
}