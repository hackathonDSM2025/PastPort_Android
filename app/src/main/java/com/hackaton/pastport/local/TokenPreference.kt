package com.hackaton.pastport.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class TokenPreference(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("token_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val ACCESS_TOKEN_KEY = "accessToken"
    }

    fun setAccessToken(token: String) {
        prefs.edit { putString(ACCESS_TOKEN_KEY, "Bearer $token") }
    }
    fun getAccessToken(): String? {
        return prefs.getString(ACCESS_TOKEN_KEY, null)
    }
    fun clearAccessToken() {
        prefs.edit { remove(ACCESS_TOKEN_KEY) }
    }
}