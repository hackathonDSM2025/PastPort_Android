package com.hackaton.pastport.network

import com.hackaton.pastport.network.model.auth.DuplicateIdRequest
import com.hackaton.pastport.network.model.auth.DuplicateIdResponse
import com.hackaton.pastport.network.model.auth.SignResponse
import com.hackaton.pastport.network.model.auth.SignRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

private const val AUTH = "/api/auth"

interface AuthApi {
    @POST("$AUTH/check-username")
    suspend fun duplicateId(@Body request: DuplicateIdRequest): Response<DuplicateIdResponse>

    @POST("$AUTH/register")
    suspend fun signUp(@Body request: SignRequest): Response<SignResponse>

    @POST("$AUTH/login")
    suspend fun login(@Body request: SignRequest): Response<SignResponse>
}