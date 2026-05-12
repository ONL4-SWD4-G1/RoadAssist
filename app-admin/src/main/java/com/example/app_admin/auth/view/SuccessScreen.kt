package com.example.app_admin.auth.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.auth.view.component.AuthBackgroundDecoration
import com.example.app_admin.auth.view.component.AuthHeaderText
import com.example.app_admin.auth.view.component.PrimaryButton
import com.example.app_admin.theme.IstighathaColors

@Composable
fun SuccessScreen(
    onGoToLoginClick: () -> Unit,
    modifier: Modifier = Modifier.Companion,
    title: String = "تم تغيير كلمة المرور بنجاح",
    subtitle: String = "يمكنك الآن تسجيل الدخول باستخدام كلمة المرور الجديدة"
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(IstighathaColors.Surface)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.Companion.size(120.dp),
            contentAlignment = Alignment.Companion.Center
        ) {
            Text(
                text = "✓",
                fontSize = 80.sp,
                color = IstighathaColors.Success,
                fontWeight = FontWeight.Companion.Bold
            )
        }

        Spacer(modifier = Modifier.Companion.height(32.dp))

        AuthHeaderText(
            title = title,
            subtitle = subtitle
        )

        Spacer(modifier = Modifier.Companion.height(48.dp))

        Box(modifier = Modifier.Companion.padding(horizontal = 16.dp)) {
            PrimaryButton(
                text = "الذهاب لتسجيل الدخول",
                onClick = onGoToLoginClick
            )
        }

        Spacer(modifier = Modifier.Companion.weight(1f))

        AuthBackgroundDecoration()
    }
}