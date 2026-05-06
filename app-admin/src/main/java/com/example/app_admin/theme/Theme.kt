package com.example.app_admin.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// هنا نستخدم ألوانكِ التي عرفناها في ملف Color.kt
private val LightColorScheme = lightColorScheme(
    primary = PrimaryOrange,      // اللون الأساسي (البرتقالي)
    onPrimary = Color.White,
    secondary = NavyBlue,         // اللون الثانوي (الكحلي)
    onSecondary = Color.White,
    background = BackgroundGray,  // لون الخلفية
    surface = Color.White,
    onSurface = NavyBlue,
    error = Color.Red
)

// يمكنكِ تعريف ألوان للـ Dark Mode هنا إذا أردتِ، أو استعارة نفس ألوان الـ Light مؤقتاً
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryOrange,
    secondary = LightBlueGray,
    background = NavyBlue,
    surface = NavyBlue,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun RoadAssistTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // عطلتها هنا لنضمن ظهور ألوانكِ الأصلية بدلاً من ألوان النظام
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // تأكدي أن ملف Typography.kt موجود ولا يوجد به أخطاء
        content = content
    )
}