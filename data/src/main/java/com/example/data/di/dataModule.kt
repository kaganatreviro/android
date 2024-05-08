package com.example.data.di

import com.example.data.BuildConfig.BASE_URL
import com.example.data.remote.api_services.BeverageApiService
import com.example.data.remote.api_services.UserApiService
import okhttp3.OkHttpClient
import com.example.data.repositories.BeverageRepositoryImpl
import com.example.data.repositories.EstablishmentRepositoryImpl
import com.example.data.repositories.UserRepositoryImpl
import com.example.domain.repositories.BeverageRepository
import com.example.domain.repositories.UserRepository
import com.example.data.local.prefs.TokenPrefs
import com.example.data.remote.api_services.EstablishmentApiService
import com.example.domain.repositories.EstablishmentRepository
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    factoryOf(::provideRetrofit)
    factoryOf(::provideOkHttpClient)
    factoryOf(::provideBeverageApi)
    factoryOf(::provideUserApi)
    factoryOf(::provideEstablishmentApi)
    singleOf(::BeverageRepositoryImpl) {
        bind<BeverageRepository>()
    }
    singleOf(::EstablishmentRepositoryImpl) {
        bind<EstablishmentRepository>()
    }
    singleOf(::UserRepositoryImpl) {
        bind<UserRepository>()
    }
    singleOf(::TokenPrefs)
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
}


fun provideBeverageApi(retrofit: Retrofit): BeverageApiService {
    return retrofit.create(BeverageApiService::class.java)
}

fun provideUserApi(retrofit: Retrofit): UserApiService {
    return retrofit.create(UserApiService::class.java)
}

fun provideEstablishmentApi(retrofit: Retrofit): EstablishmentApiService{
    return retrofit.create(EstablishmentApiService::class.java)
}