package com.example.data.remote.interceptors

import android.util.Log
import androidx.paging.LOG_TAG
import com.example.data.local.prefs.TokenPrefs
import com.example.data.remote.api_services.AuthApiService
import com.example.data.remote.dto.RefreshTokenRequestDto
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val authApiService: AuthApiService,
    private val tokenPrefs: TokenPrefs
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (responseCount(response) >= MAX_RETRIES) {
            return null
        }
        if (response.code == 401) {
            try {
                tokenPrefs.refresh?.let {
                    val refreshedRequest = runBlocking {
                        authApiService.refreshToken(RefreshTokenRequestDto(it))
                    }
                    Log.e("ololo", refreshedRequest.code().toString())
                    Log.e("ololo", refreshedRequest.message())
//                    tokenPrefs.access = refreshedRequest.access
//                    tokenPrefs.refresh = refreshedRequest.refresh
                    return response.request.newBuilder()
                        .header("Authorization", "Bearer ${tokenPrefs.access}")
                        .build()
                }
            } catch (e: Exception) {
                Log.e("ololo", "ebat")
            }
        }
        return null
    }

    private fun responseCount(response: Response): Int {
        var result = 1
        var current = response.priorResponse
        while (current != null) {
            result++
            current = current.priorResponse
        }
        return result
    }

    companion object {
        private const val MAX_RETRIES = 3
    }
}