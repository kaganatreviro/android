package com.example.presentation.di

import com.example.domain.di.domainModule
import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.example.presentation.ui.fragments.search.SearchViewModel
import com.example.presentation.ui.fragments.profile.ProfileViewModel
import com.example.presentation.ui.fragments.home.HomeViewModel
import com.example.presentation.ui.fragments.establishment.EstablishmentDetailViewModel
import com.example.domain.use_cases.GetEstablishmentMenuByIdUseCase
import org.koin.dsl.module

val mainModule = module {
    includes(domainModule)
    viewModelOf(::SearchViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::EstablishmentDetailViewModel)
}