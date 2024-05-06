package com.example.domain.di

import org.koin.core.module.dsl.factoryOf
import com.example.domain.use_cases.GetBeverageUserCase
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetBeverageUserCase)
}
