package com.hackaton.pastport.network.model.auth

data class SignResponse(
    val success: Boolean,
    val data: SignResponseData
)

data class SignResponseData(
    val userId: Int,
    val token: String
)