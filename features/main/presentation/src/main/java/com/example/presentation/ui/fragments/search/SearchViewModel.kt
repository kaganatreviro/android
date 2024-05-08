package com.example.presentation.ui.fragments.search

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.Beverage
import com.example.domain.use_cases.GetBeverageUserCase
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel(
    private val getBeverageUserCase: GetBeverageUserCase
) : BaseViewModel() {

    private val _beveragesState = mutableUiStateFlow<List<Beverage>>()
    val beveragesState = _beveragesState.asStateFlow()

    init {
        getBeverageUserCase()
    }
}