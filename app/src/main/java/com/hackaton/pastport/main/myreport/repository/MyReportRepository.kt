package com.hackaton.pastport.main.myreport.repository

import com.hackaton.pastport.network.UserApi
import com.hackaton.pastport.network.model.user.GetReviewListResponse
import com.hackaton.pastport.utils.Token
import com.hackaton.pastport.utils.apiCall
import javax.inject.Inject

class MyReportRepository @Inject constructor(
    private val api: UserApi,
    private val token: Token
) {
    private val accessToken = token.getAccessToken()

    suspend fun getReportList(): Result<GetReviewListResponse> {
        if (accessToken.isNullOrEmpty()) {
            return Result.failure(Error("Token is not found"))
        }
        return apiCall("getReportList") { api.getReviewList(accessToken) }
    }
}