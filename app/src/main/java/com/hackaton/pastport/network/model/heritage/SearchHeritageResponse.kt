package com.hackaton.pastport.network.model.heritage

import com.hackaton.pastport.network.model.BaseResponse

typealias SearchHeritageResponse = BaseResponse<SearchHeritageResponseData>

data class SearchHeritageResponseData(
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val imageUrl: String,
    val description: String
)
