package com.example.roadassist

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roadassist.ui.theme.DarkBlue
import com.example.roadassist.ui.theme.OrangeAccent
import com.example.roadassist.ui.theme.White
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(onNavigateToOnboarding: () -> Unit) {

    val scale = remember { Animatable(0.6f) }
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        delay(1_800)
        onNavigateToOnboarding()
    }

    Box(
        modifier = Modifier.fillMaxSize().background(DarkBlue),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.scale(scale.value)
        ) {

            Box(
                modifier = Modifier.size(160.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "RoadAssist Logo",
                    modifier = Modifier.size(160.dp),
                    contentScale = ContentScale.Fit
                )
            }

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
private fun Divider(color: Color, width: androidx.compose.ui.unit.Dp) {
    Box(
        modifier = Modifier.width(width).height(1.dp).background(color)
    )
}