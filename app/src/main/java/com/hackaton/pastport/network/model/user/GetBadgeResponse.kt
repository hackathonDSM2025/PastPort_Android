package com.hackaton.pastport.network.model.user

import com.hackaton.pastport.network.model.BaseResponse

typealias GetBadgeResponse = BaseResponse<GetBadgeResponseData>

data class GetBadgeResponseData(
    val badgeCount: Int,
    val badges: List<BadgeData>
)

data class BadgeData(
    val name: String,
    val imageUrl: String,
    val earnedAt: String,
    val heritageName: String
)
