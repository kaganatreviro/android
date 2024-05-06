package com.example.data.local.prefs

import android.content.Context
import android.content.SharedPreferences

class TokenPrefs(private val context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }

    var access: String?
        get() = getTokenByKey(ACCESS_TOKEN_KEY)

        set(value) {
            setTokenByKey(ACCESS_TOKEN_KEY, value)
        }

    var refresh: String?
        get() {
            return getTokenByKey(REFRESH_TOKEN_KEY)
        }
        set(value) {
            setTokenByKey(REFRESH_TOKEN_KEY, value)
        }

    private fun getTokenByKey(key: String): String? =
        sharedPreferences.getString(key, null)

    private fun setTokenByKey(key: String, value: String?) {
        sharedPreferences
            .edit()
            .putString(key, value)
            .apply()
    }

    fun clearUserData() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        const val ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY"
        const val REFRESH_TOKEN_KEY = "REFRESH_TOKEN_KEY"
    }
}