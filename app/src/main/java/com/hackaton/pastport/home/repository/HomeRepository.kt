package com.hackaton.pastport.home.repository

import com.hackaton.pastport.network.UserApi
import com.hackaton.pastport.network.model.user.GetBadgeResponse
import com.hackaton.pastport.network.model.user.GetReviewCountResponse
import com.hackaton.pastport.network.model.user.GetVisitedCountResponse
import com.hackaton.pastport.utils.Token
import com.hackaton.pastport.utils.apiCall
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: UserApi,
    private val token: Token
) {
    private val accessToken = token.getAccessToken()

    suspend fun getVisitedCount(): Result<GetVisitedCountResponse> {
        if (accessToken.isNullOrEmpty()) {
            return Result.failure(Error("Token is not found"))
        }
        return apiCall("getVisitedCount") { api.getVisitedCount(accessToken, true) }
    }

    suspend fun getReviewCount(): Result<GetReviewCountResponse> {
        if (accessToken.isNullOrEmpty()) {
            return Result.failure(Error("Token is not found"))
        }
        return apiCall("getReviewCount") { api.getReviewsCount(accessToken, true) }
    }

    suspend fun getBadges(): Result<GetBadgeResponse> {
        if (accessToken.isNullOrEmpty()) {
            return Result.failure(Error("Token is not found"))
        }
        return apiCall("getBadges") { api.getBadges(accessToken) }
    }
}