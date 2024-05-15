package com.example.domain.di

import org.koin.core.module.dsl.factoryOf
import com.example.domain.use_cases.GetBeverageUserCase
import com.example.domain.use_cases.GetUserUseCase
import com.example.domain.use_cases.GetEstablishmentListUseCase
import com.example.domain.use_cases.LogoutUseCase
import com.example.domain.use_cases.GetEstablishmentMenuByIdUseCase
import com.example.domain.use_cases.GetEstablishmentDetailsByIdUseCase
import com.example.domain.use_cases.UpdateUserDataUseCase
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetBeverageUserCase)
    factoryOf(::GetUserUseCase)
    factoryOf(::GetEstablishmentListUseCase)
    factoryOf(::LogoutUseCase)
    factoryOf(::GetEstablishmentMenuByIdUseCase)
    factoryOf(::GetEstablishmentDetailsByIdUseCase)
    factoryOf(::UpdateUserDataUseCase)
}
