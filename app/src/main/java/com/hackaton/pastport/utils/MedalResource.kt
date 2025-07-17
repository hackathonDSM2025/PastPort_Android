package com.hackaton.pastport.utils

import com.hackaton.pastport.R

fun getMedalResource(name: String): Int? {
    return when (name) {
        "경복궁" -> R.drawable.medal_gyeongbok
        "불국사" -> R.drawable.medal_bulguk
        "고인돌" -> R.drawable.medal_dolmen
        "하회마을" -> R.drawable.medal_hahoe
        "남한산성" -> R.drawable.medal_namhansanseong
        "탑골공원" -> R.drawable.medal_tapgol
        else -> null
    }
}