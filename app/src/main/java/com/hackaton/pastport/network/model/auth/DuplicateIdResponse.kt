package com.hackaton.pastport.network.model.auth

import com.hackaton.pastport.network.model.BaseResponse

typealias DuplicateIdResponse = BaseResponse<DuplicateIdResponseData>

data class DuplicateIdResponseData(
    val available: Boolean,
    val message: String
)
