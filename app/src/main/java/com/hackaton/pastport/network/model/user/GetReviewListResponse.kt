package com.hackaton.pastport.network.model.user

import com.hackaton.pastport.network.model.BaseResponse

typealias GetReviewListResponse = BaseResponse<GetReviewListResponseData>

data class GetReviewListResponseData(
    val reviews: List<ReviewListItemData>
)

data class ReviewListItemData(
    val reviewId: Int,
    val heritageId: Int,
    val heritageName: String,
    val heritageImageUrl: String,
    val rating: Int,
    val reviewText: String,
    val createdAt: String,
    val updatedAt: String
)