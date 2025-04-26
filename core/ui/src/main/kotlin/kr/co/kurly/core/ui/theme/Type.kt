package kr.co.kurly.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kr.co.kurly.core.ui.R

internal val mainFontFamily = FontFamily(
    Font(R.font.pretendard_regular, weight = FontWeight.Normal),
    Font(R.font.pretendard_bold, weight = FontWeight.Bold),
    Font(R.font.pretendard_extrabold, weight = FontWeight.ExtraBold),
)

internal val mainTypography = MainTypography(
    header01R = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Normal,
        color = MainColor.black,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.02).sp
    ),
    header01EB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.ExtraBold,
        color = MainColor.black,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.02).sp
    ),
    title01EB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.ExtraBold,
        color = MainColor.black,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.02).sp
    ),
    body01B = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Bold,
        color = MainColor.black,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.02).sp
    ),
    body01R = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Normal,
        color = MainColor.black,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.02).sp
    )
)

@Immutable
data class MainTypography(
    val header01R: TextStyle = TextStyle.Default,
    val header01EB: TextStyle = TextStyle.Default,
    val title01EB: TextStyle = TextStyle.Default,
    val body01B: TextStyle = TextStyle.Default,
    val body01R: TextStyle = TextStyle.Default
)

internal val LocalMainTypography = staticCompositionLocalOf { MainTypography() }