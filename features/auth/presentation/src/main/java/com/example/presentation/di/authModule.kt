package com.example.presentation.di

import com.example.domain.di.authDomainModule
import org.koin.dsl.module

val authModule = module {
    includes(authDomainModule)
}