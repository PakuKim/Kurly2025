package kr.co.kurly.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object MainColor {
    val black = Color(0xFF1B1C1E)
    val white = Color(0xFFFFFFFF)

    val grey01 = Color(0xFF464646)
    val grey02 = Color(0xFF6B6B6B)
    val grey03 = Color(0xFF909090)
    val grey04 = Color(0xFFB5B5B5)
    val grey05 = Color(0xFFDADADA)
    val grey06 = Color(0xFFEEEEEE)
    val grey07 = Color(0xFFF8F8F8)

    val keyColor = Color(0xFF0096AA)
    val keyColor02 = Color(0xFF37C3D6)
    val keyColor03 = keyColor02.copy(alpha = 0.6f)
    val keyColor04 = keyColor02.copy(alpha = 0.4f)
    val keyColor04N = Color(0xFFAFE7EF)
    val keyColor05 = keyColor02.copy(alpha = 0.2f)
    val keyColor05N = Color(0xFFD7F3F7)
    val keyColor06 = keyColor02.copy(alpha = 0.1f)
    val keyColor06N = Color(0xFFEBF9FB)

    val subColor = Color(0xFF032974)
    val subColor02 = Color(0xFF32A6EB)
    val subColor03 = subColor02.copy(alpha = 0.8f)
    val subColor03N = Color(0xFF5BB8EF)
    val subColor04 = subColor02.copy(alpha = 0.6f)
    val subColor05 = subColor02.copy(alpha = 0.4f)
    val subColor06 = subColor02.copy(alpha = 0.2f)
    val subColor07 = subColor02.copy(alpha = 0.1f)

    val error = Color(0xFFF75640)
    val etc = Color(0xFFF9BAB5)
    val background = Color(0xFFF1F3F4)
}

internal val LightColorScheme = lightColorScheme(
    primary = MainColor.keyColor,
    onPrimary = MainColor.white,
    primaryContainer = MainColor.keyColor03,
    onPrimaryContainer = MainColor.keyColor06,
    secondary = MainColor.subColor,
    onSecondary = MainColor.white,
    secondaryContainer = MainColor.subColor03,
    onSecondaryContainer = MainColor.subColor06,
    tertiary = MainColor.subColor02,
    onTertiary = MainColor.white,
    tertiaryContainer = MainColor.subColor07,
    onTertiaryContainer = MainColor.subColor03,
    error = MainColor.error,
    onError = MainColor.white,
    errorContainer = MainColor.error.copy(alpha = 0.8f),
    onErrorContainer = MainColor.error.copy(alpha = 0.1f),
    background = MainColor.white,
    onBackground = MainColor.white,
    surface = MainColor.white,
    onSurface = MainColor.black,
    surfaceVariant = MainColor.white,
    onSurfaceVariant = MainColor.grey02,
    inverseSurface = MainColor.grey03,
    inverseOnSurface = MainColor.grey04,
    outline = MainColor.black,
)

internal val DarkColorScheme = darkColorScheme(

)