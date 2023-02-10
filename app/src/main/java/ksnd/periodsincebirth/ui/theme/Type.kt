package ksnd.periodsincebirth.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun typography(): Typography {
    val fontFamily = FontFamily.Default
    return Typography(
        headlineLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
        ),
        titleLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 26.sp,
        ),
        titleMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
        ),
        titleSmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
        ),
        bodySmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        ),
        labelLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
        ),
        labelMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        ),
    )
}
