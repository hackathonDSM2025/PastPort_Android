package com.hackaton.pastport.network.model.user

import com.hackaton.pastport.network.model.BaseResponse

typealias GetMyInfoResponse = BaseResponse<GetMyInfoResponseData>

data class GetMyInfoResponseData(
    val userId: Int,
    val username: String,
    val createdAt: String
)
