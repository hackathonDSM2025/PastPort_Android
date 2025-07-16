package com.hackaton.pastport.auth.login.repository

import com.hackaton.pastport.network.AuthApi
import com.hackaton.pastport.network.model.auth.SignRequest
import com.hackaton.pastport.network.model.auth.SignResponse
import com.hackaton.pastport.utils.Token
import com.hackaton.pastport.utils.apiCall
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val api: AuthApi,
    private val token: Token
) {
    suspend fun login(request: SignRequest): Result<SignResponse> {
        return apiCall("login") { api.login(request) }
            .onSuccess { token.saveToken(it.data.token) }
    }
}