package com.example.roadassist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roadassist.ui.theme.Background
import com.example.roadassist.ui.theme.DarkBlue
import com.example.roadassist.ui.theme.TextDark
import com.example.roadassist.ui.theme.TextGray
import com.example.roadassist.ui.theme.White

data class NotificationItem(
    val message: String,
    val time: String
)

@Composable
fun NotificationsScreen(onBackClick: () -> Unit = {}) {

    val notifications = listOf(
        NotificationItem("Your service provider is on the way. Arriving in 5 mins.", "Now"),
        NotificationItem("5% discount on annual subscription!", "min ago"),
        NotificationItem("10% discount on first service", "1 day ago")
    )

    Scaffold(containerColor = Background) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues))
        {
            TopBarWithBack(title = "Notification", onBackClick = onBackClick)
            Spacer(modifier = Modifier.height(8.dp))
            notifications.forEach { NotificationCard(notification = it) }
        }
    }
}

@Composable
fun TopBarWithBack(title: String, onBackClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(DarkBlue)
            .padding(horizontal = 8.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                Icons.Default.ArrowBackIosNew,
                contentDescription = "Back",
                tint = White
            )
        }
        Text(
            text = title,
            color = White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(48.dp))
    }
}

@Composable
fun NotificationCard(notification: NotificationItem) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(White)
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            notification.message,
            fontSize = 13.sp,
            color = TextDark,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            notification.time,
            fontSize = 11.sp,
            color = TextGray
        )
    }
}
