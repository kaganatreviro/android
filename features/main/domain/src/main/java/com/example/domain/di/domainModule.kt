package com.example.domain.di

import org.koin.core.module.dsl.factoryOf
import com.example.domain.use_cases.GetBeveragesUserCase
import com.example.domain.use_cases.GetUserUseCase
import com.example.domain.use_cases.GetEstablishmentListUseCase
import com.example.domain.use_cases.LogoutUseCase
import com.example.domain.use_cases.GetEstablishmentMenuByIdUseCase
import com.example.domain.use_cases.UpdateUserDataUseCase
import com.example.domain.use_cases.GetBeverageByIdUseCase
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetBeveragesUserCase)
    factoryOf(::GetUserUseCase)
    factoryOf(::GetEstablishmentListUseCase)
    factoryOf(::LogoutUseCase)
    factoryOf(::GetEstablishmentMenuByIdUseCase)
    factoryOf(::UpdateUserDataUseCase)
    factoryOf(::GetBeverageByIdUseCase)
}
