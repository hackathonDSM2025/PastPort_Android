package com.hackaton.pastport.main.qr.repository

import com.hackaton.pastport.network.HeritageApi
import com.hackaton.pastport.network.model.heritage.WriteReviewRequest
import com.hackaton.pastport.utils.Token
import com.hackaton.pastport.utils.apiCall
import javax.inject.Inject

class WriteReviewRepository @Inject constructor(
    private val api: HeritageApi,
    private val token: Token
) {
    private val accessToken = token.getAccessToken()

    suspend fun writeReport(reviewText: String): Result<Unit> {
        if (accessToken.isNullOrEmpty()) {
            return Result.failure(Error("Token is not found"))
        }
        return apiCall("writeReport") { api.writeReview(accessToken, 1, WriteReviewRequest(1, reviewText))}
    }
}