package com.example.data.di

import com.example.data.BuildConfig.BASE_URL
import com.example.data.local.prefs.TokenPrefs
import com.example.data.remote.api_services.AuthApiService
import com.example.data.remote.api_services.BeverageApiService
import com.example.data.remote.api_services.UserApiService
import com.example.data.remote.interceptors.TokenAuthenticator
import com.example.data.remote.interceptors.AuthInterceptor
import com.example.data.repositories.AuthRepositoryImpl
import com.example.data.repositories.BeverageRepositoryImpl
import com.example.data.repositories.UserRepositoryImpl
import com.example.domain.repositories.AuthRepository
import com.example.domain.repositories.BeverageRepository
import com.example.domain.repositories.UserRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    singleOf(::UserRepositoryImpl) {
        bind<UserRepository>()
    }
    singleOf(::AuthRepositoryImpl) {
        bind<AuthRepository>()
    }
    singleOf(::TokenPrefs)

    //ApiServices
    singleOf(::provideBeverageApi)
    singleOf(::provideUserApi)
    single<AuthApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder()
                    .addInterceptor(get<HttpLoggingInterceptor>())
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
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