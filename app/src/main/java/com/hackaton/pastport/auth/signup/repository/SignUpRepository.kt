package com.hackaton.pastport.auth.signup.repository

import com.hackaton.pastport.network.AuthApi
import com.hackaton.pastport.network.model.auth.DuplicateIdRequest
import com.hackaton.pastport.network.model.auth.DuplicateIdResponse
import com.hackaton.pastport.network.model.auth.SignResponse
import com.hackaton.pastport.network.model.auth.SignRequest
import com.hackaton.pastport.utils.Token
import com.hackaton.pastport.utils.apiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpRepository @Inject constructor(
    private val api: AuthApi,
    private val token: Token
) {
    suspend fun duplicateId(request: DuplicateIdRequest): Result<DuplicateIdResponse> {
        return apiCall("duplicateId") { api.duplicateId(request) }
    }

    suspend fun signUp(request: SignRequest): Result<SignResponse> {
        return apiCall("signUp") { api.signUp(request) }
            .onSuccess { token.saveToken(it.data.token) }
    }
}