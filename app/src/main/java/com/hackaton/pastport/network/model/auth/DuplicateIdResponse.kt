package com.hackaton.pastport.network.model.auth

data class DuplicateIdResponse(
    val success: Boolean,
    val data: DuplicateIdResponseData
)

data class DuplicateIdResponseData(
    val available: Boolean,
    val message: String
)
