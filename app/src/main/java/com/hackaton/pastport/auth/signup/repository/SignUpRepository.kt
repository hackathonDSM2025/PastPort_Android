package com.hackaton.pastport.auth.signup.repository

import com.hackaton.pastport.network.AuthApi
import com.hackaton.pastport.network.model.auth.DuplicateIdRequest
import com.hackaton.pastport.network.model.auth.DuplicateIdResponse
import com.hackaton.pastport.network.model.auth.SignResponse
import com.hackaton.pastport.network.model.auth.SignUpRequest
import com.hackaton.pastport.utils.Token
import com.hackaton.pastport.utils.apiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpRepository @Inject constructor(
    private val api: AuthApi,
    private val token: Token
) {
    suspend fun duplicateId(username: String): Result<DuplicateIdResponse> {
        return apiCall("duplicateId") { api.duplicateId(DuplicateIdRequest(username)) }
    }

    suspend fun signUp(username: String, password: String): Result<SignResponse> {
        return apiCall("signUp") { api.signUp(SignUpRequest(username, password)) }
            .onSuccess { token.saveToken(it.data.token) }
    }
}