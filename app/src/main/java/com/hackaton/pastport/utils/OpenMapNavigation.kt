package com.hackaton.pastport.utils

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun openMapNavigation(context: Context, lat: Float, lng: Float) {
    val geoUri = "geo:$lat,$lng?q=$lat,$lng".toUri()
    val intent = Intent(Intent.ACTION_VIEW, geoUri)
    val chooser = Intent.createChooser(intent, "지도 앱으로 열기")
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(chooser)
    } else {
        val webUri = "https://www.google.com/maps/search/?api=1&query=$lat,$lng".toUri()
        val webIntent = Intent(Intent.ACTION_VIEW, webUri)
        context.startActivity(webIntent)
    }
}