package com.example.data.remote.interceptors

import com.example.data.local.prefs.TokenPrefs
import com.example.data.remote.api_services.AuthApiService
import com.example.data.remote.api_services.UserApiService
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenPrefs: TokenPrefs
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .header("Authorization", "Bearer ${tokenPrefs.access}")
                .build()
        )
    }
}