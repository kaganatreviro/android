package com.example.presentation.di

import com.example.domain.di.authDomainModule
import com.example.presentation.ui.fragments.sign_up.SignUpViewModel
import com.example.presentation.ui.fragments.login.LoginViewModel
import com.example.presentation.ui.fragments.forgotPassword.ForgotPasswordViewModel
import com.example.presentation.ui.fragments.changePassword.ChangePasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    includes(authDomainModule)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::ForgotPasswordViewModel)
    viewModelOf(::ChangePasswordViewModel)
}