package com.example.data.storage

import android.content.Context
import android.content.SharedPreferences

class Preferences(val context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }

    var access_token: String
        get() {
            return getStringByKey(PrefKeys.SESSION_ID)
        }
        set(value) {
            setStringByKey(PrefKeys.SESSION_ID, value)
        }

    var refresh_token: String
        get() {
            return getStringByKey(PrefKeys.SESSION_ID)
        }
        set(value) {
            setStringByKey(PrefKeys.SESSION_ID, value)
        }

    private fun getStringByKey(key: String): String =
        sharedPreferences.getString(key, String.empty) ?: String.empty

    private fun setStringByKey(key: String, value: String) {
        sharedPreferences
            .edit()
            .putString(key, value)
            .apply()
    }

    fun clearUserData() {
        refresh_token = ""
        access_token = ""
    }

    object PrefKeys {
        const val SESSION_ID = "SESSION_ID"
    }
}