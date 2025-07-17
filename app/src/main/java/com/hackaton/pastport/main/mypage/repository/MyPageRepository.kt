package com.hackaton.pastport.main.mypage.repository

import com.hackaton.pastport.network.UserApi
import com.hackaton.pastport.network.model.user.GetMyInfoResponse
import com.hackaton.pastport.utils.Token
import com.hackaton.pastport.utils.apiCall
import javax.inject.Inject

class MyPageRepository @Inject constructor(
    private val api: UserApi,
    private val token: Token
) {
    private val accessToken = token.getAccessToken()

    suspend fun getMyInfo(): Result<GetMyInfoResponse> {
        if (accessToken.isNullOrEmpty()) {
            return Result.failure(Error("Token is not found"))
        }
        return apiCall("getMyInfo") { api.getUserInfo(accessToken) }
    }
}