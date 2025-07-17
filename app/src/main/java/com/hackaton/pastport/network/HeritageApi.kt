package com.hackaton.pastport.network

import com.hackaton.pastport.network.model.heritage.SearchHeritageResponse
import com.hackaton.pastport.network.model.heritage.WriteReviewRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

private const val HERITAGE = "/api/heritage"

interface HeritageApi {
    @GET(HERITAGE)
    suspend fun searchHeritage(
        @Header("Authorization") token: String,
        @Query("keyword") keyword: String
    ): Response<SearchHeritageResponse>

    @POST("$HERITAGE/{heritageId}/reviews")
    suspend fun writeReview(
        @Header("Authorization") token: String,
        @Path("heritageId") heritageId: Int,
        @Body request: WriteReviewRequest
    ): Response<Unit>
}