package com.hackaton.pastport.network

import com.hackaton.pastport.network.model.user.GetBadgeResponse
import com.hackaton.pastport.network.model.user.GetMyInfoResponse
import com.hackaton.pastport.network.model.user.GetReviewCountResponse
import com.hackaton.pastport.network.model.user.GetVisitedCountResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val USER = "/api/users/me"

interface UserApi {
    @GET("$USER")
    suspend fun getUserInfo(
        @Header("Authorization") token: String
    ): Response<GetMyInfoResponse>

    @GET("$USER/visits")
    suspend fun getVisitedCount(
        @Header("Authorization") token: String,
        @Query("count_only") countOnly: Boolean
    ): Response<GetVisitedCountResponse>

    @GET("$USER/reviews")
    suspend fun getReviewsCount(
        @Header("Authorization") token: String,
        @Query("count_only") countOnly: Boolean
    ): Response<GetReviewCountResponse>

    @GET("$USER/badges")
    suspend fun getBadges(
        @Header("Authorization") token: String
    ): Response<GetBadgeResponse>
}