package com.hackaton.pastport.main.mymedal.repository

import com.hackaton.pastport.network.BadgeApi
import com.hackaton.pastport.network.model.badge.GetBadgeListResponse
import com.hackaton.pastport.utils.Token
import com.hackaton.pastport.utils.apiCall
import javax.inject.Inject

class MyMedalRepository @Inject constructor(
    private val api: BadgeApi,
    private val token: Token
) {
    private val accessToken = token.getAccessToken()
    suspend fun getMyMedal(): Result<GetBadgeListResponse> {
        if (accessToken.isNullOrEmpty()) {
            return Result.failure(Error("Token is not found"))
        }
        return apiCall("getMyMedal") { api.getBadgeList(accessToken) }
    }
}