package com.hackaton.pastport.network.model.badge

import com.hackaton.pastport.network.model.BaseResponse

typealias GetBadgeListResponse = BaseResponse<GetBadgeListResponseData>

data class GetBadgeListResponseData(
    val badges: List<BadgeListItemData>
)

data class BadgeListItemData(
    val badgeId: Int,
    val name: String,
    val imageUrl: String,
    val earnedAt: String,
    val heritageName: String,
    val description: String
)