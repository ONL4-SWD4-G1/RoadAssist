package com.example.roadassist.features.profile.about.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.R
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.features.profile.about.viewmodel.AboutViewModel
import com.example.roadassist.theme.CardBg
import com.example.roadassist.theme.NavyBlue
import com.example.roadassist.theme.TextPrimary
import com.example.roadassist.theme.TextSecondary

@Composable
fun AboutUsScreen(
    onNavigateBack: () -> Unit,
    viewModel: AboutViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UserTopBar("About us", onNavigateBack) },
        containerColor = Color.White,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(12.dp))
            Image(
                painter = painterResource(R.drawable.app_icon),
                contentDescription = "App Icon",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )
            Spacer(Modifier.height(36.dp))
            InfoCard(
                icon = Icons.Filled.Info,
                title = "About Quick Repair",
                body = "Quick Repair is your 24/7 roadside assistance partner. We provide reliable and convenient service, prioritizing customer satisfaction and safety.",
            )
            Spacer(Modifier.height(16.dp))
            InfoCard(
                icon = Icons.Filled.Flag,
                title = "Our Mission",
                body = "We are dedicated to providing you with the highest quality service.",
                boldPrefix = "To make roadside assistance a stress-free experience.",
            )
            Spacer(Modifier.height(8.dp))
            Text("Version ${uiState.appVersion}", fontSize = 12.sp, color = TextSecondary)
        }
    }
}

@Composable
private fun InfoCard(icon: ImageVector, title: String, body: String, boldPrefix: String = "") {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(0.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, null, tint = NavyBlue, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary,
                            fontSize = 16.sp
                        )
                    ) {
                        append(boldPrefix)
                    }
                    withStyle(SpanStyle(color = TextSecondary, fontSize = 16.sp)) {
                        append(body)
                    }
                },
                lineHeight = 26.sp,
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AboutUsScreenPreview() {
    AboutUsScreen(onNavigateBack = {})
}
