package com.hackaton.pastport.main.map.repository

import com.hackaton.pastport.network.HeritageApi
import com.hackaton.pastport.network.model.heritage.SearchHeritageResponse
import com.hackaton.pastport.utils.Token
import com.hackaton.pastport.utils.apiCall
import javax.inject.Inject

class MapRepository @Inject constructor(
    private val api: HeritageApi,
    private val token: Token
) {
    private val accessToken = token.getAccessToken()

    suspend fun searchHeritage(keyword: String): Result<SearchHeritageResponse> {
        if (accessToken.isNullOrEmpty()) {
            return Result.failure(Error("Token is not found"))
        }
        return apiCall("searchHeritage") { api.searchHeritage(accessToken, keyword)}
    }
}