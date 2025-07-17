package com.hackaton.pastport.network

import com.hackaton.pastport.network.model.heritage.SearchHeritageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val HERITAGE = "/api/heritage"

interface HeritageApi {
    @GET(HERITAGE)
    suspend fun searchHeritage(
        @Header("Authorization") token: String,
        @Query("keyword") keyword: String
    ): Response<SearchHeritageResponse>
}