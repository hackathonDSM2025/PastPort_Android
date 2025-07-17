package com.hackaton.pastport.network

import com.hackaton.pastport.network.model.badge.GetBadgeListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

private const val BADGE = "/api/badges"

interface BadgeApi {
    @GET(BADGE)
    suspend fun getBadgeList(
        @Header("Authorization") token: String
    ): Response<GetBadgeListResponse>
}