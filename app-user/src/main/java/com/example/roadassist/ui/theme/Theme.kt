package com.example.roadassist.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val LightColors = lightColorScheme(
    primary        = NavyBlue,
    onPrimary      = White,
    secondary      = OrangeButton,
    onSecondary    = White,
    background     = White,
    onBackground   = TextPrimary,
    surface        = White,
    onSurface      = TextPrimary,
    surfaceVariant = OffWhite,
)

@Composable
fun RoadAssistTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}