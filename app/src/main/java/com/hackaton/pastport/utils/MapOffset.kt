package com.hackaton.pastport.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val minLat = 33.0
private const val maxLat = 39.5
private const val minLng = 124.5
private const val maxLng = 130.0

fun dpFromLatitude(lat: Float, mapHeightDp: Dp): Dp {
    val percentage = 1 - (lat - minLat) / (maxLat - minLat)
    return (percentage * mapHeightDp.value).dp
}

fun dpFromLongitude(lng: Float, mapWidthDp: Dp): Dp {
    val percentage = (lng - minLng) / (maxLng - minLng)
    return (percentage * mapWidthDp.value).dp
}