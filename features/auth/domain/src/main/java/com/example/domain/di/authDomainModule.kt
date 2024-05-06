package com.example.domain.di

import org.koin.core.module.dsl.factoryOf
import com.example.domain.use_cases.UserLoginUseCase
import org.koin.dsl.module

val authDomainModule = module {
    factoryOf(::UserLoginUseCase)
}