package com.example.data.local.prefs

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SubscriptionPrefs(private val context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("subscription_prefs", Context.MODE_PRIVATE)
    }

    var isActive: Boolean
        get() {
            return getBooleanByKey(SUBSCRIPTION_IS_ACTIVE)
        }
        set(value) {
            setBooleanByKey(SUBSCRIPTION_IS_ACTIVE, value)
        }

    private fun getBooleanByKey(key: String): Boolean =
        sharedPreferences.getBoolean(key, false)

    private fun setBooleanByKey(key: String, value: Boolean) {
        sharedPreferences
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    fun saveSubscriptionData(id: String, name: String, endDate: String) {
        sharedPreferences.edit().apply {
            putString("id", id)
            putString("name", name)
            putString("endDate", endDate)
            apply()
        }
    }

    fun getSubscriptionData(): Triple<String, String, String> {
        val id = sharedPreferences.getString("id", "") ?: ""
        val name = sharedPreferences.getString("name", "") ?: ""
        val endDate = sharedPreferences.getString("endDate", "") ?: ""
        return Triple(id, name, endDate)
    }

    @SuppressLint("CommitPrefEdits")
    fun clearSubscriptionData(){
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        const val SUBSCRIPTION_IS_ACTIVE = "SUBSCRIPTION_IS_ACTIVE"
    }

}