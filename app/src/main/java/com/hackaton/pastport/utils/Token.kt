package com.hackaton.pastport.utils

import com.hackaton.pastport.local.TokenPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Token @Inject constructor(
    private val pref: TokenPreference
) {
    fun getAccessToken(): String? = pref.getAccessToken()

    fun saveToken(accessToken: String) {
        pref.setAccessToken(accessToken)
    }

    fun clearToken() {
        pref.clearAccessToken()
    }
}