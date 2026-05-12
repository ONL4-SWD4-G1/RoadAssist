package com.example.app_admin.auth

import androidx.compose.runtime.compositionLocalOf

val LocalAdminLogout = compositionLocalOf<() -> Unit> { { } }
