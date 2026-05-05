package com.example.roadassist.splash.view

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.R
import com.example.roadassist.splash.vm.SplashViewModel
import com.example.roadassist.ui.theme.DarkBlue
import com.example.roadassist.ui.theme.White

@Composable
fun SplashScreen(
    onNavigateToOnboarding: () -> Unit,
    viewModel: SplashViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.navigateToOnboarding) {
        if (uiState.navigateToOnboarding) {
            viewModel.onNavigationHandled()
            onNavigateToOnboarding()
        }
    }

    val scale = remember { Animatable(0.6f) }
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }

    SplashContent(scale = scale.value)
}

@Composable
private fun SplashContent(scale: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.scale(scale)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "RoadAssist Logo",
                modifier = Modifier.size(160.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "RoadAssist",
                color = White,
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Divider(color = White.copy(alpha = 0.5f), width = 40.dp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "24/7 ROADSIDE ASSISTANCE",
                    color = White.copy(alpha = 0.8f),
                    fontSize = 11.sp,
                    letterSpacing = 1.5.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Divider(color = White.copy(alpha = 0.5f), width = 40.dp)
            }
        }
    }
}

@Composable
private fun Divider(color: Color, width: Dp) {
    Box(modifier = Modifier.width(width).height(1.dp).background(color))
}