package com.hackaton.pastport.auth.signup.repository

import com.hackaton.pastport.network.AuthApi
import com.hackaton.pastport.network.model.auth.DuplicateIdRequest
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
    suspend fun duplicateId(id: String): Result<Unit> {
        return apiCall("duplicateId") { api.duplicateId(DuplicateIdRequest(id)) }
    }

    suspend fun signUp(id: String, password: String): Result<SignResponse> {
        return apiCall("signUp") { api.signUp(SignUpRequest(id, password)) }
            .onSuccess { token.saveToken(it.data.token) }
    }
}