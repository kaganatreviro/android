package com.example.presentation.di

import com.example.domain.di.domainModule
import org.koin.dsl.module

val authModule = module {
    includes(domainModule)
}