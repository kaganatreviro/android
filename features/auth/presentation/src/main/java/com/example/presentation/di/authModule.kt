package com.example.presentation.di

import com.example.domain.di.authDomainModule
import com.example.presentation.ui.fragments.regist.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    includes(authDomainModule)
    viewModelOf(::SignUpViewModel)
}