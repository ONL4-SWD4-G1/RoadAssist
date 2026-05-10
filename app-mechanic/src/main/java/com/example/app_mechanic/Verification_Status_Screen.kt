package com.example.app_mechanic

import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// ─── Screen ───────────────────────────────────────────────────────────────────
@Composable
fun VerificationScreen() {

    // Pulse animation for the outer glow ring
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue  = 0.92f,
        targetValue   = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    Scaffold(
        containerColor = BgDark,
        topBar         = { VerificationTopBar() }
    ) { padding ->
        Column(
            modifier              = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
            horizontalAlignment   = Alignment.CenterHorizontally,
            verticalArrangement   = Arrangement.SpaceBetween
        ) {
            Spacer(Modifier.height(24.dp))

            // ── Top Section ──────────────────────────────────────────────────
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                // Glowing document icon
                Box(
                    modifier         = Modifier
                        .size(160.dp)
                        .scale(pulseScale)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(GlowBlue, Color(0xFF0D1117)),
                                radius = 320f
                            )
                        )
                        .border(1.5.dp, AccentBlueDark, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    // Inner circle
                    Box(
                        modifier         = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF162035))
                            .border(1.dp, AccentBlueDark, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Outlined.Description,
                            contentDescription = null,
                            tint               = AccentBlue,
                            modifier           = Modifier.size(40.dp)
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                // ANALYZING badge
                Row(
                    modifier          = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFF162035))
                        .border(1.dp, AccentBlueDark, RoundedCornerShape(50))
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(AccentBlue)
                    )
                    Text(
                        text          = "ANALYZING",
                        color         = AccentBlue,
                        fontSize      = 11.sp,
                        fontWeight    = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    )
                }

                Spacer(Modifier.height(32.dp))

                // Title
                Text(
                    text       = "Verification in Progress",
                    color      = TextPrimary,
                    fontSize   = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign  = TextAlign.Center
                )

                Spacer(Modifier.height(12.dp))

                // Subtitle
                Text(
                    text      = "Our team is currently reviewing your documents. This process usually takes 24–48 hours.",
                    color     = TextSecondary,
                    fontSize  = 14.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )

                Spacer(Modifier.height(28.dp))

                // Info card
                Row(
                    modifier          = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(CardDark)
                        .padding(horizontal = 18.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier         = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF162035)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Outlined.NotificationsActive,
                            contentDescription = null,
                            tint               = AccentBlue,
                            modifier           = Modifier.size(22.dp)
                        )
                    }
                    Column {
                        Text(
                            text       = "Stay Notified",
                            color      = TextPrimary,
                            fontSize   = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text       = "We'll send you a push notification as soon as your account status is updated.",
                            color      = TextSecondary,
                            fontSize   = 13.sp,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            // ── Bottom Buttons ───────────────────────────────────────────────
            Column(
                horizontalAlignment   = Alignment.CenterHorizontally,
                modifier              = Modifier.padding(bottom = 32.dp)
            ) {
                // Check Status
                Button(
                    onClick  = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape  = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AccentBlue)
                ) {
                    Text(
                        text       = "Check Status",
                        color      = Color.White,
                        fontSize   = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(Modifier.height(12.dp))

                // Logout
                OutlinedButton(
                    onClick  = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape  = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary),
                    border = BorderStroke(1.dp, Color(0xFF21262D))
                ) {
                    Text(
                        text       = "Logout",
                        fontSize   = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(Modifier.height(18.dp))

                // Need help?
                Row {
                    Text("Need help? ", color = TextMuted, fontSize = 13.sp)
                    Text(
                        text       = "Contact Support",
                        color      = AccentBlue,
                        fontSize   = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

// ─── Top Bar ──────────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationTopBar() {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Outlined.ArrowBackIos,
                    contentDescription = "Back",
                    tint               = TextPrimary,
                    modifier           = Modifier.size(20.dp)
                )
            }
        },
        title = {
            Text(
                text          = "VERIFICATION",
                color         = TextPrimary,
                fontSize      = 14.sp,
                fontWeight    = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = BgDark)
    )
}

// ─── Preview ──────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF0D1117, showSystemUi = true)
@Composable
fun VerificationScreenPreview() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        VerificationScreen()
    }
}