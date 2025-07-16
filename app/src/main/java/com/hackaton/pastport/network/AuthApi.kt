package com.hackaton.pastport.network

import com.hackaton.pastport.network.model.auth.DuplicateIdRequest
import com.hackaton.pastport.network.model.auth.SignResponse
import com.hackaton.pastport.network.model.auth.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/")
    suspend fun duplicateId(@Body request: DuplicateIdRequest): Response<Unit>

    @POST("/register")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignResponse>
}