package com.example.week9.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val week9ColorScheme = lightColorScheme(
    primary = Color(0xFF111111),
    onPrimary = Color.White,
    background = Color(0xFFF5F5F5),
    onBackground = Color(0xFF111111),
    surface = Color.White,
    onSurface = Color(0xFF111111),
)

@Composable
fun Week9Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = week9ColorScheme,
        content = content,
    )
}



