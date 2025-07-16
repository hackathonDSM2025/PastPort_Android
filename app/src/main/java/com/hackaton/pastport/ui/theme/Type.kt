package com.hackaton.pastport.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackaton.pastport.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val Pretendard = FontFamily(
    listOf(
        Font(
            resId = R.font.pretendard_regular,
            weight = FontWeight.Normal
        ),
        Font(
            resId = R.font.pretendard_medium,
            weight = FontWeight.Medium
        ),
        Font(
            resId = R.font.pretendard_semibold,
            weight = FontWeight.SemiBold
        ),
        Font(
            resId = R.font.pretendard_bold,
            weight = FontWeight.Bold
        )
    )
)

object PastPortFontStyle {
    private val defaultTextColor = Black

    private fun createTextStyle(weight: FontWeight, size: Int) = TextStyle(
        fontFamily = Pretendard,
        fontWeight = weight,
        fontSize = size.sp,
        color = defaultTextColor
    )

    val medium14 = createTextStyle(FontWeight.Medium, 14)
    val medium16 = createTextStyle(FontWeight.Medium, 16)

    val bold16 = createTextStyle(FontWeight.Bold, 16)
    val bold36 = createTextStyle(FontWeight.Bold, 36)
}