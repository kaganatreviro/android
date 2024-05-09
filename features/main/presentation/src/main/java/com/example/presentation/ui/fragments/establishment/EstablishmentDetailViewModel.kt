package com.example.presentation.ui.fragments.establishment

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.EstablishmentDetails
import com.example.domain.models.Menu
import com.example.domain.use_cases.GetEstablishmentListUseCase
import com.example.domain.use_cases.GetEstablishmentMenuByIdUseCase
import kotlinx.coroutines.flow.asStateFlow

class EstablishmentDetailViewModel(
    private val getEstablishmentMenuUseCase: GetEstablishmentMenuByIdUseCase
) : BaseViewModel() {

    private val _establishmentMenuState = mutableUiStateFlow<List<Menu>>()
    val establishmentMenuState = _establishmentMenuState.asStateFlow()

    fun getEstablishmentMenuById(id: Int) {
        getEstablishmentMenuUseCase(id).gatherRequest(_establishmentMenuState)
    }
}