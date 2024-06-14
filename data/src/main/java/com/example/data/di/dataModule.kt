package com.example.data.di

import com.example.data.BuildConfig.BASE_URL
import com.example.data.local.prefs.TokenPrefs
import com.example.data.remote.api_services.AuthApiService
import com.example.data.remote.api_services.BeverageApiService
import com.example.data.remote.api_services.EstablishmentApiService
import com.example.data.remote.api_services.OrderApiService
import com.example.data.remote.api_services.SubscriptionApiService
import com.example.data.remote.api_services.UserApiService
import com.example.data.remote.interceptors.TokenAuthenticator
import com.example.data.remote.interceptors.AuthInterceptor
import com.example.data.repositories.AuthRepositoryImpl
import com.example.data.repositories.BeverageRepositoryImpl
import com.example.data.repositories.UserRepositoryImpl
import com.example.domain.repositories.AuthRepository
import com.example.data.repositories.OrderRepositoryImpl
import com.example.domain.repositories.BeverageRepository
import com.example.domain.repositories.EstablishmentRepository
import com.example.data.repositories.EstablishmentRepositoryImpl
import com.example.data.repositories.SubscriptionRepositoryImpl
import com.example.domain.repositories.OrderRepository
import com.example.domain.repositories.SubscriptionRepository
import com.example.domain.repositories.UserRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    //Retrofit
    singleOf(::provideRetrofit)
    singleOf(::provideOkHttpClientWithAuth)
    factory<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    singleOf(::AuthInterceptor)
    singleOf(::TokenAuthenticator)

    //Repositories
    singleOf(::BeverageRepositoryImpl) {
        bind<BeverageRepository>()
    }
    singleOf(::EstablishmentRepositoryImpl){
        bind<EstablishmentRepository>()
    }
    singleOf(::UserRepositoryImpl) {
        bind<UserRepository>()
    }
    singleOf(::AuthRepositoryImpl) {
        bind<AuthRepository>()
    }
    singleOf(::OrderRepositoryImpl) {
        bind<OrderRepository>()
    }
    singleOf(::SubscriptionRepositoryImpl) {
        bind<SubscriptionRepository>()
    }
    singleOf(::TokenPrefs)

    //ApiServices
    singleOf(::provideBeverageApi)
    singleOf(::provideUserApi)
    singleOf(::provideEstablishmentApi)
    singleOf(::provideOrderApi)
    singleOf(::provideSubscriptionApi)

    single<AuthApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .client(
                OkHttpClient().newBuilder()
                    .addInterceptor(get<HttpLoggingInterceptor>())
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build()
            )
            .build()
            .create(AuthApiService::class.java)
    }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClientWithAuth(
    loggingInterceptor: HttpLoggingInterceptor,
    authInterceptor: AuthInterceptor,
    authenticator: TokenAuthenticator
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(authInterceptor)
        .authenticator(authenticator)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()
}

fun provideBeverageApi(retrofit: Retrofit): BeverageApiService {
    return retrofit.create(BeverageApiService::class.java)
}

fun provideUserApi(retrofit: Retrofit): UserApiService {
    return retrofit.create(UserApiService::class.java)
}

fun provideOrderApi(retrofit: Retrofit): OrderApiService{
    return retrofit.create(OrderApiService::class.java)
}

fun provideEstablishmentApi(retrofit: Retrofit): EstablishmentApiService{
    return retrofit.create(EstablishmentApiService::class.java)
}

fun provideSubscriptionApi(retrofit: Retrofit): SubscriptionApiService{
    return retrofit.create(SubscriptionApiService::class.java)
}