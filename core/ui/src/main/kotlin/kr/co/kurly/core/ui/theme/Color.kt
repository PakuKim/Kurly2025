package kr.co.kurly.core.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object MainColor {
    val black = Color(0xFF1B1C1E)
    val white = Color(0xFFFFFFFF)
    val grey = Color(0xFF6B6B6B)
    val keyColor = Color(0xFF633DA8)
    val error = Color(0xFFF75640)
    val orange = Color(0xFFFA622F)
}

internal val LightColorScheme = lightColorScheme(
    primary = MainColor.keyColor,
    onPrimary = MainColor.white,
    primaryContainer = MainColor.white,
    onPrimaryContainer = MainColor.white,
    secondary = MainColor.white,
    onSecondary = MainColor.white,
    secondaryContainer = MainColor.white,
    onSecondaryContainer = MainColor.white,
    error = MainColor.error,
    background = MainColor.white,
    onBackground = MainColor.white,
    surface = MainColor.white,
    onSurface = MainColor.white,
    surfaceVariant = MainColor.white,
    onSurfaceVariant = MainColor.white,
    outline = MainColor.black,
)