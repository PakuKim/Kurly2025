package kr.co.kurly.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object MainTheme {
    val typography: MainTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalMainTypography.current
}

@Composable
fun MainTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalMainTypography provides mainTypography) {
        MaterialTheme(
            colorScheme = LightColorScheme,
            content = content
        )
    }
}
