package com.hackaton.pastport.network.model.user

import com.hackaton.pastport.network.model.BaseResponse

typealias GetVisitedCountResponse = BaseResponse<GetVisitedCountResponseData>

data class GetVisitedCountResponseData(
    val visitCount: Int
)
