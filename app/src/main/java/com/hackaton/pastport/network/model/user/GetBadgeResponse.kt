package com.hackaton.pastport.network.model.user

data class GetBadgeResponse(
    val success: Boolean,
    val data: GetBadgeResponseData
)

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
