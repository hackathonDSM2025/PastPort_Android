package com.hackaton.pastport.network.model.user

data class GetReviewCountResponse(
    val success: Boolean,
    val data: GetReviewCountResponseData
)

data class GetReviewCountResponseData(
    val reviewCount: Int
)