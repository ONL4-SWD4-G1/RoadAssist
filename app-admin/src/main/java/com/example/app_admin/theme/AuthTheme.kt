package com.example.app_admin.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Color Palette - Istighatha Design System
object IstighathaColors {
    // Primary Colors
    val Primary = Color(0xFF875200)          // #875200
    val PrimaryContainer = Color(0xFFEC9513)  // #EC9513
    val OnPrimary = Color(0xFFFFFFFF)
    val OnPrimaryContainer = Color(0xFF5A3500)

    // Secondary Colors
    val Secondary = Color(0xFF1E293B)         // Navy Blue
    val OnSecondary = Color(0xFFFFFFFF)
    val SecondaryContainer = Color(0xFFD5E0F8)
    val OnSecondaryContainer = Color(0xFF586377)

    // Tertiary (Accent)
    val Tertiary = Color(0xFF00658F)
    val OnTertiary = Color(0xFFFFFFFF)
    val TertiaryContainer = Color(0xFF16B3F9)
    val OnTertiaryContainer = Color(0xFF00425F)

    // Surface Colors
    val Surface = Color(0xFFFFF8F4)           // Warm off-white
    val SurfaceContainer = Color(0xFFFBEBDD)
    val OnSurface = Color(0xFF221A12)
    val OnSurfaceVariant = Color(0xFF534434)

    // Error
    val Error = Color(0xFFBA1A1A)
    val OnError = Color(0xFFFFFFFF)
    val ErrorContainer = Color(0xFFFFDAD6)

    // Success (Green)
    val Success = Color(0xFF2E7D32)

    // Outline & Borders
    val Outline = Color(0xFF867462)
    val OutlineVariant = Color(0xFFD9C3AE)
}

private val LightColorScheme = lightColorScheme(
    primary = IstighathaColors.Primary,
    primaryContainer = IstighathaColors.PrimaryContainer,
    secondary = IstighathaColors.Secondary,
    secondaryContainer = IstighathaColors.SecondaryContainer,
    tertiary = IstighathaColors.Tertiary,
    tertiaryContainer = IstighathaColors.TertiaryContainer,
    error = IstighathaColors.Error,
    background = IstighathaColors.Surface,
    surface = IstighathaColors.Surface,
    surfaceContainer = IstighathaColors.SurfaceContainer,
    onPrimary = IstighathaColors.OnPrimary,
    onSecondary = IstighathaColors.OnSecondary,
    onTertiary = IstighathaColors.OnTertiary,
    onBackground = IstighathaColors.OnSurface,
    onSurface = IstighathaColors.OnSurface,
    onError = IstighathaColors.OnError,
    outline = IstighathaColors.Outline,
    outlineVariant = IstighathaColors.OutlineVariant,
)

@Composable
fun IstighathaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = IstighathaTypography,
        content = content
    )
}