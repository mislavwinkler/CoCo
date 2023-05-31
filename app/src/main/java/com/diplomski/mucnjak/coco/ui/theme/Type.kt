@file:Suppress("DEPRECATION")

package com.diplomski.mucnjak.coco.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.diplomski.mucnjak.coco.R

private val CocoFontFamily = FontFamily(
    Font(resId = R.font.comfortaa_regular, weight = FontWeight.Normal),
    Font(resId = R.font.comfortaa_medium, weight = FontWeight.Medium),
    Font(resId = R.font.comfortaa_bold, weight = FontWeight.Bold),
    Font(resId = R.font.comfortaa_semibold, weight = FontWeight.SemiBold)
)

// Hrv: Tekuci tekst
/**
 * Size is 32
 */
private val FlowingText = TextStyle(
    fontFamily = CocoFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 21.sp,
    letterSpacing = 0.sp,
)

// Hrv: Podnaslovi
/**
 * Size is 36
 */
private val Subtitle2 = TextStyle(
    fontFamily = CocoFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 24.sp,
    letterSpacing = 0.sp,
)

// Hrv: Naslovi
/**
 * Size is 40
 */
private val Subtitle1 = TextStyle(
    fontFamily = CocoFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 27.sp,
    letterSpacing = 0.sp,
)

// Hrv: Veliki brojevi

/**
 * Size is 36
 */
private val ConfirmButton = TextStyle(
    fontFamily = CocoFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 24.sp,
    letterSpacing = 3.sp,
)

/**
 * Size is 128
 */
private val H1 = TextStyle(
    fontFamily = CocoFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 85.sp,
    letterSpacing = 10.sp,
)

/**
 * Size is 96
 */
private val H2 = TextStyle(
    fontFamily = CocoFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 64.sp,
    letterSpacing = 8.sp
)

/**
 * Size is 64
 */
private val H3 = TextStyle(
    fontFamily = CocoFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 43.sp,
    letterSpacing = 0.sp,
)

/**
 * Size is 26
 */
private val caption = TextStyle(
    fontFamily = CocoFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 17.sp,
    letterSpacing = 0.sp
)

/**
 * @param WelcomeName size is 55
 * @param WelcomeHello size is 40
 * @param StartButton size is 48
 * @param Loading size is 48
 */
data class SpecialTypography(
    val WelcomeName: TextStyle,
    val WelcomeHello: TextStyle,
    val StartButton: TextStyle,
    val Loading: TextStyle,
)

val cocoSpecialTypography: SpecialTypography = SpecialTypography(
    WelcomeName = TextStyle(
        fontFamily = CocoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 37.sp,
        letterSpacing = 0.sp
    ),
    WelcomeHello = TextStyle(
        fontFamily = CocoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 27.sp,
        letterSpacing = 0.sp
    ),
    StartButton = TextStyle(
        fontFamily = CocoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        letterSpacing = 8.sp
    ),
    Loading = TextStyle(
        fontFamily = CocoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        letterSpacing = 8.sp
    ),
)

val LocalSpecialTypography: ProvidableCompositionLocal<SpecialTypography> =
    compositionLocalOf { cocoSpecialTypography }

// Set of Material typography styles to start with
val Typography: Typography = Typography(
    defaultFontFamily = CocoFontFamily,
    h1 = H1,
    h2 = H2,
    h3 = H3,
    subtitle1 = Subtitle1,
    subtitle2 = Subtitle2,
    body1 = FlowingText,
    button = ConfirmButton,
    caption = caption,
)
