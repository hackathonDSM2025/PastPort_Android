package com.hackaton.pastport.network.model.user

import com.hackaton.pastport.network.model.BaseResponse

typealias  GetReviewCountResponse = BaseResponse<GetReviewCountResponseData>

data class GetReviewCountResponseData(
    val reviewCount: Int
)