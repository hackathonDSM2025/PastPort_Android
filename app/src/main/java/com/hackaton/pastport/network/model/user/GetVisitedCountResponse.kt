package com.hackaton.pastport.network.model.user

data class GetVisitedCountResponse(
    val success: Boolean,
    val data: GetVisitedCountResponseData
)

data class GetVisitedCountResponseData(
    val visitCount: Int
)
