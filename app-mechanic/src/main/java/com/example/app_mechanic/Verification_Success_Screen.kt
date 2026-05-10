package com.example.app_mechanic

import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.SettingsInputAntenna
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// ─── Screen ───────────────────────────────────────────────────────────────────
@Composable
fun VerificationSuccessScreen() {

    // Pulse animation for green glow ring
    val infiniteTransition = rememberInfiniteTransition(label = "glow")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue  = 0.88f,
        targetValue   = 1.08f,
        animationSpec = infiniteRepeatable(
            animation  = tween(1600, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue  = 0.3f,
        targetValue   = 0.7f,
        animationSpec = infiniteRepeatable(
            animation  = tween(1600, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
    ) {
        // ── Floating decorative dots ─────────────────────────────────────────
        Box(
            modifier = Modifier
                .size(8.dp)
                .offset(x = 200.dp, y = 90.dp)
                .clip(CircleShape)
                .background(DotBlue)
        )
        Box(
            modifier = Modifier
                .size(6.dp)
                .offset(x = 36.dp, y = 220.dp)
                .clip(CircleShape)
                .background(GreenMain)
        )

        Column(
            modifier              = Modifier.fillMaxSize(),
            horizontalAlignment   = Alignment.CenterHorizontally,
            verticalArrangement   = Arrangement.SpaceBetween
        ) {
            // ── Close Button ─────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 52.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    modifier         = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF1C2333))
                        .border(1.dp, Color(0xFF21262D), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Outlined.Close,
                        contentDescription = "Close",
                        tint               = TextPrimary,
                        modifier           = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            // ── Main content ─────────────────────────────────────────────────
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier            = Modifier
                    .weight(1f)
                    .padding(horizontal = 28.dp)
            ) {
                Spacer(Modifier.height(16.dp))

                // Glow + icon
                Box(
                    modifier         = Modifier.size(180.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Outer glow ring (animated)
                    Box(
                        modifier = Modifier
                            .size(170.dp)
                            .scale(pulseScale)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(
                                        GreenGlow.copy(alpha = glowAlpha),
                                        Color.Transparent
                                    )
                                )
                            )
                            .border(1.5.dp, GreenRing.copy(alpha = glowAlpha), CircleShape)
                    )
                    // Mid ring
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF0D2818))
                            .border(2.dp, GreenDark.copy(alpha = 0.5f), CircleShape)
                    )
                    // Green circle with check
                    Box(
                        modifier         = Modifier
                            .size(88.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color(0xFF34D75A), GreenMain)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = null,
                            tint               = Color.White,
                            modifier           = Modifier.size(44.dp)
                        )
                    }
                }

                Spacer(Modifier.height(32.dp))

                // Title
                Text(
                    text       = "Verification\nSuccessful!",
                    color      = TextPrimary,
                    fontSize   = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign  = TextAlign.Center,
                    lineHeight = 40.sp
                )

                Spacer(Modifier.height(14.dp))

                // Subtitle
                Text(
                    text       = "Welcome to the network. Your profile is now live and ready for dispatch.",
                    color      = TextSecondary,
                    fontSize   = 15.sp,
                    textAlign  = TextAlign.Center,
                    lineHeight = 23.sp
                )

                Spacer(Modifier.height(28.dp))

                // ── Go Online Card ────────────────────────────────────────────
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(CardDark)
                        .padding(16.dp)
                ) {
                    // Header row
                    Row(
                        modifier          = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // WiFi/broadcast icon
                        Box(
                            modifier         = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(AccentBlueDim),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Outlined.SettingsInputAntenna,
                                contentDescription = null,
                                tint               = AccentBlue,
                                modifier           = Modifier.size(22.dp)
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Go Online",
                                color      = TextPrimary,
                                fontSize   = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                "Ready for requests",
                                color    = TextSecondary,
                                fontSize = 12.sp
                            )
                        }
                        // Green status dot
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(GreenMain)
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    // Map placeholder
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(MapOverlay)
                    ) {
                        // Fake map grid lines
                        MapPlaceholder()

                        // Blue location pin
                        Box(
                            modifier         = Modifier
                                .align(Alignment.Center)
                                .offset(x = 20.dp, y = (-10).dp)
                                .size(14.dp)
                                .clip(CircleShape)
                                .background(AccentBlue)
                                .border(2.dp, Color.White, CircleShape)
                        )

                        // "San Francisco" label
                        Text(
                            text     = "San Francisco",
                            color    = TextPrimary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(y = 14.dp)
                        )

                        // ACTIVE JOBS NEARBY badge
                        Row(
                            modifier          = Modifier
                                .align(Alignment.BottomStart)
                                .padding(10.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(BadgeBg)
                                .padding(horizontal = 8.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text          = "ACTIVE JOBS NEARBY",
                                color         = TextSecondary,
                                fontSize      = 9.sp,
                                fontWeight    = FontWeight.Bold,
                                letterSpacing = 0.8.sp
                            )
                            Box(
                                modifier         = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(AccentBlue)
                                    .padding(horizontal = 5.dp, vertical = 1.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text       = "12",
                                    color      = Color.White,
                                    fontSize   = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            // ── Bottom Buttons ────────────────────────────────────────────────
            Column(
                modifier            = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick  = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape  = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AccentBlue)
                ) {
                    Text(
                        text       = "Get Started",
                        color      = Color.White,
                        fontSize   = 17.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.width(8.dp))
                    Icon(
                        Icons.Outlined.ArrowForward,
                        contentDescription = null,
                        tint               = Color.White,
                        modifier           = Modifier.size(20.dp)
                    )
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text          = "REQUIRES LOCATION PERMISSIONS",
                    color         = TextMuted,
                    fontSize      = 10.sp,
                    fontWeight    = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
            }
        }
    }
}

// ─── Map Placeholder ────────────────────────────────────────────────────────
@Composable
private fun MapPlaceholder() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val gridColor = Color(0xFF243447)
        val step = 28.dp.toPx()
        // Horizontal lines
        var y = 0f
        while (y < size.height) {
            drawLine(gridColor, start = Offset(0f, y), end = Offset(size.width, y), strokeWidth = 1f)
            y += step
        }
        // Vertical lines
        var x = 0f
        while (x < size.width) {
            drawLine(gridColor, start = Offset(x, 0f), end = Offset(x, size.height), strokeWidth = 1f)
            x += step
        }
        // Diagonal road lines for realism
        drawLine(
            color       = Color(0xFF2A3F52),
            start       = Offset(0f, size.height * 0.4f),
            end         = Offset(size.width, size.height * 0.55f),
            strokeWidth = 4f
        )
        drawLine(
            color       = Color(0xFF2A3F52),
            start       = Offset(size.width * 0.3f, 0f),
            end         = Offset(size.width * 0.45f, size.height),
            strokeWidth = 3f
        )
    }
}

// ─── Preview ──────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF0A0E14, showSystemUi = true)
@Composable
fun VerificationSuccessPreview() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        VerificationSuccessScreen()
    }
}