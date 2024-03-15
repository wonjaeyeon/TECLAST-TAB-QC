package com.teclast_korea.teclast_qc_application.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF363636),
    primaryVariant = Color(0xFFFF5A26),
    secondary = Color(0xFF000000),
    secondaryVariant = Color(0xFF03DAC6),
    background = Color(0xFF000000),
    surface = Color(0xFF121212),
    error = Color(0xFFCF6679),

    onPrimary = Color(0xFFFFFFFF), // text icon color for the primary color
    onSecondary = Color(0xFFFFFFFF),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xff868686),
    onError = Color(0xFFFFFFFF),
)

private val LightColorPalette = lightColors(
    primary = Color(0xFFFFFFFF),
    primaryVariant = Color(0xFFFF5A26),
    secondary = Color(0xffdadada),
    secondaryVariant = Color(0xFF0333C6),
    background = Color(0xffc2c2c2),
    surface = Color(0xFF121212),
    error = Color(0xFFCF6679),

    onPrimary = Color(0xff2f2f2f), // text icon color for the primary color
    onSecondary = Color(0xff2f2f2f),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xff868686),
    onError = Color(0xFFFFFFFF),


    /* Other default colors to override
    background = MaterialTheme.colors.onPrimary,
    surface = MaterialTheme.colors.onPrimary,
    onPrimary = MaterialTheme.colors.onPrimary,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
    // past color primaryVariant = Color(0xffff7a16),
)


@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}