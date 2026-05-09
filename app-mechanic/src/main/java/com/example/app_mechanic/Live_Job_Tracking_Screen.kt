package com.example.mechanicapp.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

//Colors
private val DarkBg         = Color(0xFF0D1117)
private val CardBg         = Color(0xFF161B26)
private val CardBg2        = Color(0xFF1A2035)
private val AccentBlue     = Color(0xFF3B82F6)
private val AccentGreen    = Color(0xFF22C55E)
private val TextPrimary    = Color(0xFFFFFFFF)
private val TextSecondary  = Color(0xFF64748B)
private val TextMuted      = Color(0xFF94A3B8)
private val LiveGreen      = Color(0xFF4ADE80)
private val EarningsBlue   = Color(0xFF60A5FA)
private val DividerColor   = Color(0xFF1E2535)
private val ProgressTrack  = Color(0xFF1E2535)
private val DoneGreen      = Color(0xFF16A34A)
private val ActiveBlue     = Color(0xFF2563EB)

//Task Step Data
enum class StepStatus { DONE, ACTIVE, UPCOMING }

data class TaskStep(
    val title: String,
    val subtitle: String,
    val status: StepStatus,
    val icon: ImageVector
)

//Main Screen
@Composable
fun JobInProgressScreen(onBack: () -> Unit = {}) {

    // Live timer: 00:24:15
    var hours   by remember { mutableStateOf(0) }
    var minutes by remember { mutableStateOf(24) }
    var seconds by remember { mutableStateOf(15) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            seconds++
            if (seconds >= 60) { seconds = 0; minutes++ }
            if (minutes >= 60) { minutes = 0; hours++ }
        }
    }

    val steps = listOf(
        TaskStep("Arrived",           "Mechanic reached location at 14:15",  StepStatus.DONE,     Icons.Default.Check),
        TaskStep("Diagnosing",        "Issue identified: Front left puncture",StepStatus.DONE,     Icons.Default.Check),
        TaskStep("Repairing Tire",    "In Progress...",                       StepStatus.ACTIVE,   Icons.Default.Build),
        TaskStep("Final Inspection",  "Upcoming",                             StepStatus.UPCOMING, Icons.Default.Assignment),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            //Top Bar
            JobInProgressTopBar(onBack = onBack)

            //Scrollable Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Spacer(modifier = Modifier.height(2.dp))

                // Timer
                TimerSection(hours = hours, minutes = minutes, seconds = seconds)

                // Customer Card
                CustomerCard()

                // Estimated Earnings Card
                EstimatedEarningsCard()

                // Current Task
                CurrentTaskSection(steps = steps)

                Spacer(modifier = Modifier.height(16.dp))
            }

            //Finish Job Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkBg)
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AccentBlue,
                        contentColor   = Color.White
                    )
                ) {
                    Text(
                        text       = "Finish Job",
                        fontSize   = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

//Top Bar
@Composable
fun JobInProgressTopBar(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBg)
            .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack, modifier = Modifier.size(36.dp)) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = TextPrimary,
                modifier = Modifier.size(22.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text       = "Job in Progress",
                color      = TextPrimary,
                fontSize   = 17.sp,
                fontWeight = FontWeight.Bold,
                textAlign  = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(LiveGreen)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text      = "LIVE STATUS",
                    color     = LiveGreen,
                    fontSize  = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )
            }
        }

        // Call button
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(AccentBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "Call",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

//Timer Section
@Composable
fun TimerSection(hours: Int, minutes: Int, seconds: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TimerBlock(value = hours.toString().padStart(2, '0'),   label = "HOURS",   modifier = Modifier.weight(1f))
        TimerBlock(value = minutes.toString().padStart(2, '0'), label = "MINUTES", modifier = Modifier.weight(1f))
        TimerBlock(value = seconds.toString().padStart(2, '0'), label = "SECONDS", modifier = Modifier.weight(1f))
    }
}

@Composable
fun TimerBlock(value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(CardBg)
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text       = value,
                color      = TextPrimary,
                fontSize   = 34.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
        Text(
            text      = label,
            color     = TextSecondary,
            fontSize  = 11.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.8.sp
        )
    }
}

//Customer Card
@Composable
fun CustomerCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape    = RoundedCornerShape(16.dp),
        colors   = CardDefaults.cardColors(containerColor = CardBg)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text      = "CUSTOMER",
                    color     = TextSecondary,
                    fontSize  = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text       = "John Doe",
                    color      = TextPrimary,
                    fontSize   = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text  = "Tesla Model 3 • White",
                    color = TextMuted,
                    fontSize = 13.sp
                )
            }

            // Map thumbnail
            Box(
                modifier = Modifier
                    .size(width = 90.dp, height = 65.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFCBD5E1))
            ) {
                // Simulated map
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFFBFDBFE), Color(0xFF93C5FD))
                            )
                        )
                )
                // Grid lines
                repeat(3) { i ->
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (15 + i * 18).dp),
                        color    = Color.White.copy(alpha = 0.5f),
                        thickness = 1.dp
                    )
                }
                // Location pin
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(22.dp)
                        .clip(CircleShape)
                        .background(AccentBlue)
                        .border(2.dp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(13.dp)
                    )
                }
            }
        }
    }
}

//Estimated Earnings Card
@Composable
fun EstimatedEarningsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape    = RoundedCornerShape(16.dp),
        colors   = CardDefaults.cardColors(containerColor = CardBg2)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(AccentBlue.copy(alpha = 0.18f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBalanceWallet,
                    contentDescription = null,
                    tint = AccentBlue,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text       = "Estimated Earnings",
                    color      = TextPrimary,
                    fontSize   = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text  = "Standard Service Rate",
                    color = EarningsBlue,
                    fontSize = 12.sp
                )
            }

            Text(
                text       = "\$85.00",
                color      = EarningsBlue,
                fontSize   = 22.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

//Current Task Section
@Composable
fun CurrentTaskSection(steps: List<TaskStep>) {
    Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
        Text(
            text       = "Current Task",
            color      = TextPrimary,
            fontSize   = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        steps.forEachIndexed { index, step ->
            TaskStepRow(
                step      = step,
                isLast    = index == steps.lastIndex
            )
        }
    }
}

//Task Step Row
@Composable
fun TaskStepRow(step: TaskStep, isLast: Boolean) {
    // Animated progress for active step
    val animatedProgress = remember { Animatable(0f) }
    LaunchedEffect(step.status) {
        if (step.status == StepStatus.ACTIVE) {
            animatedProgress.animateTo(
                targetValue = 0.65f,
                animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing)
            )
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        //Left: Icon + Connector Line
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(44.dp)
        ) {
            // Step icon circle
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(
                        when (step.status) {
                            StepStatus.DONE     -> DoneGreen
                            StepStatus.ACTIVE   -> ActiveBlue
                            StepStatus.UPCOMING -> CardBg
                        }
                    )
                    .then(
                        if (step.status == StepStatus.UPCOMING)
                            Modifier.border(1.dp, DividerColor, CircleShape)
                        else Modifier
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = step.icon,
                    contentDescription = null,
                    tint = when (step.status) {
                        StepStatus.DONE     -> Color.White
                        StepStatus.ACTIVE   -> Color.White
                        StepStatus.UPCOMING -> TextSecondary
                    },
                    modifier = Modifier.size(18.dp)
                )
            }

            // Connector line
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(48.dp)
                        .background(
                            when (step.status) {
                                StepStatus.DONE   -> DoneGreen
                                StepStatus.ACTIVE -> AccentBlue.copy(alpha = 0.4f)
                                else              -> DividerColor
                            }
                        )
                )
            }
        }

        Spacer(modifier = Modifier.width(14.dp))

        //Right: Content
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 6.dp, bottom = if (isLast) 0.dp else 24.dp)
        ) {
            Text(
                text = step.title,
                color = when (step.status) {
                    StepStatus.ACTIVE   -> AccentBlue
                    StepStatus.UPCOMING -> TextSecondary
                    else                -> TextPrimary
                },
                fontSize   = 15.sp,
                fontWeight = if (step.status == StepStatus.UPCOMING) FontWeight.Normal else FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text  = step.subtitle,
                color = TextSecondary,
                fontSize = 12.sp
            )

            // Progress bar for active step
            if (step.status == StepStatus.ACTIVE) {
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(ProgressTrack)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(animatedProgress.value)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(3.dp))
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(AccentBlue, Color(0xFF60A5FA))
                                )
                            )
                    )
                }
            }
        }
    }
}

//Preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JobInProgressScreenPreview() {
    MaterialTheme {
        JobInProgressScreen()
    }
}