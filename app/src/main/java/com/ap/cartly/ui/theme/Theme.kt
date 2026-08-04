package com.ap.cartly.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = CartlyBlue,
    onPrimary = Color.White,
    primaryContainer = CartlyPrimaryContainer,
    onPrimaryContainer = CartlyBlueDark,

    secondary = CartlyTeal,
    onSecondary = Color.White,
    secondaryContainer = CartlySecondaryContainer,
    onSecondaryContainer = CartlyText,

    background = CartlyBackground,
    onBackground = CartlyText,

    surface = CartlySurface,
    onSurface = CartlyText,
    onSurfaceVariant = CartlyTextSecondary
)

private val DarkColorScheme = darkColorScheme(
    primary = CartlyTeal,
    onPrimary = Color.White,

    secondary = CartlyBlue,
    onSecondary = Color.White,

    background = CartlyDarkBackground,
    onBackground = CartlyDarkText,

    surface = CartlyDarkSurface,
    onSurface = CartlyDarkText
)

@Composable
fun CartlyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}