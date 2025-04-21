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
    Font(R.font.pretendard_medium, weight = FontWeight.Medium),
    Font(R.font.pretendard_semibold, weight = FontWeight.SemiBold),
    Font(R.font.pretendard_bold, weight = FontWeight.Bold),
    Font(R.font.pretendard_extrabold, weight = FontWeight.ExtraBold),
    Font(R.font.pretendard_light, weight = FontWeight.Light),
    Font(R.font.pretendard_black, weight = FontWeight.Black)
)

internal val mainTypography = MainTypography(
    display01SB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 32.sp,
        lineHeight = 46.sp,
        letterSpacing = (-0.02).sp
    ),
    display02HV = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Black,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.02).sp
    ),
    display02EB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.02).sp
    ),
    display02SB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.02).sp
    ),
    title01EB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.02).sp
    ),
    title01SB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.02).sp
    ),
    header01EB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.02).sp
    ),
    header01SB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.02).sp
    ),
    header01R = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.02).sp
    ),
    header02EB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.02).sp
    ),
    header02SB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.02).sp
    ),
    header02R = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.02).sp
    ),
    body01EB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.02).sp
    ),
    body01SB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.02).sp
    ),
    body01R = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.02).sp
    ),
    body01M = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.02).sp
    ),
    body02EB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.02).sp
    ),
    body02SB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.02).sp
    ),
    body02R = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.02).sp
    ),
    body03EB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.02).sp
    ),
    body03SB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.02).sp
    ),
    body03R = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.02).sp
    ),
    caption01B = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.02).sp
    ),
    caption01M = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.02).sp
    ),
    caption02EB = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.02).sp
    ),
    caption02B = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.02).sp
    ),
    caption02R = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.02).sp
    )
)

@Immutable
data class MainTypography(
    val display01SB: TextStyle = TextStyle.Default,
    val display02HV: TextStyle = TextStyle.Default,
    val display02EB: TextStyle = TextStyle.Default,
    val display02SB: TextStyle = TextStyle.Default,
    val title01EB: TextStyle = TextStyle.Default,
    val title01SB : TextStyle = TextStyle.Default,
    val header01EB : TextStyle = TextStyle.Default,
    val header01SB : TextStyle = TextStyle.Default,
    val header01R : TextStyle = TextStyle.Default,
    val header02EB: TextStyle = TextStyle.Default,
    val header02SB: TextStyle = TextStyle.Default,
    val header02R: TextStyle = TextStyle.Default,
    val body01EB: TextStyle = TextStyle.Default,
    val body01SB: TextStyle = TextStyle.Default,
    val body01R: TextStyle = TextStyle.Default,
    val body01M: TextStyle = TextStyle.Default,
    val body02EB: TextStyle = TextStyle.Default,
    val body02SB: TextStyle = TextStyle.Default,
    val body02R: TextStyle = TextStyle.Default,
    val body03EB: TextStyle = TextStyle.Default,
    val body03SB: TextStyle = TextStyle.Default,
    val body03R: TextStyle = TextStyle.Default,
    val caption01B: TextStyle = TextStyle.Default,
    val caption01M: TextStyle = TextStyle.Default,
    val caption02EB: TextStyle = TextStyle.Default,
    val caption02B: TextStyle = TextStyle.Default,
    val caption02R: TextStyle = TextStyle.Default,
)

internal val LocalMainTypography = staticCompositionLocalOf { MainTypography() }