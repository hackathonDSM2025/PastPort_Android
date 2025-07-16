package com.hackaton.pastport.network.model.auth

import com.hackaton.pastport.network.model.BaseResponse

typealias SignResponse = BaseResponse<SignResponseData>

data class SignResponseData(
    val userId: Int,
    val token: String
)