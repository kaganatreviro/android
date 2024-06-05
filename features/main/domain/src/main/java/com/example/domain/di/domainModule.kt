package com.example.domain.di

import org.koin.core.module.dsl.factoryOf
import com.example.domain.use_cases.GetBeveragesUserCase
import com.example.domain.use_cases.GetUserUseCase
import com.example.domain.use_cases.GetEstablishmentListUseCase
import com.example.domain.use_cases.LogoutUseCase
import com.example.domain.use_cases.GetEstablishmentMenuByIdUseCase
import com.example.domain.use_cases.GetEstablishmentDetailsByIdUseCase
import com.example.domain.use_cases.UpdateUserDataUseCase
import com.example.domain.use_cases.GetOrderHistoryUseCase
import com.example.domain.use_cases.MakeOrderUseCase
import com.example.domain.use_cases.GetBeverageByIdUseCase
import com.example.domain.use_cases.GetEstablishmentFeedbackListUseCase
import com.example.domain.use_cases.PostFeedbackUseCase
import com.example.domain.use_cases.GetFeedbackAnswersUseCase
import com.example.domain.use_cases.PostFeedbackInAnswersUseCase
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetBeveragesUserCase)
    factoryOf(::GetUserUseCase)
    factoryOf(::GetEstablishmentListUseCase)
    factoryOf(::LogoutUseCase)
    factoryOf(::GetEstablishmentMenuByIdUseCase)
    factoryOf(::GetEstablishmentDetailsByIdUseCase)
    factoryOf(::UpdateUserDataUseCase)
    factoryOf(::GetBeverageByIdUseCase)
    factoryOf(::GetOrderHistoryUseCase)
    factoryOf(::MakeOrderUseCase)
    factoryOf(::GetEstablishmentFeedbackListUseCase)
    factoryOf(::PostFeedbackUseCase)
    factoryOf(::GetFeedbackAnswersUseCase)
    factoryOf(::PostFeedbackInAnswersUseCase)
}
