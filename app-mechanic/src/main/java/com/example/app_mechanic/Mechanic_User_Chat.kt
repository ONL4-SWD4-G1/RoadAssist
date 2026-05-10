package com.example.app_mechanic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ─── Colors ───────────────────────────────────────────────────────────────────

// ─── Data Models ──────────────────────────────────────────────────────────────
sealed class ChatMessage {
    data class Text(
        val id: Int,
        val text: String,
        val time: String,
        val isSent: Boolean,
        val isRead: Boolean = false
    ) : ChatMessage()

    data class Image(
        val id: Int,
        val caption: String,
        val time: String,
        val isSent: Boolean
    ) : ChatMessage()
}

// ─── Main Screen ──────────────────────────────────────────────────────────────
@Composable
fun ChatScreen(onBack: () -> Unit = {}) {
    var messageText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    val messages = remember {
        listOf(
            ChatMessage.Text(1, "I'm stuck near the main intersection. Engine won't start and there's a clicking sound.", "10:42 AM", false),
            ChatMessage.Text(2, "Got it. I'm on my way now. Can you check if the dashboard lights are flickering?", "10:45 AM", true, true),
            ChatMessage.Image(3, "Yes, look at this.", "10:46 AM", false),
        )
    }

    val quickReplies = listOf("I am outside", "Be there in 5 mins", "Please send location")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        // ── Top Bar ─────────────────────────────────────────────────────────
        ChatTopBar(onBack = onBack)

        Divider(color = DividerColor.copy(alpha = 0.4f), thickness = 1.dp)

        // ── Messages List ────────────────────────────────────────────────────
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            // Date label
            item {
                DateLabel(text = "TODAY")
                Spacer(modifier = Modifier.height(6.dp))
            }

            items(messages) { message ->
                when (message) {
                    is ChatMessage.Text  -> TextMessageBubble(message)
                    is ChatMessage.Image -> ImageMessageBubble(message)
                }
            }
        }

        // ── Quick Replies ─────────────────────────────────────────────────────
        QuickRepliesRow(
            replies = quickReplies,
            onReply = { messageText = it }
        )

        // ── Input Bar ─────────────────────────────────────────────────────────
        ChatInputBar(
            value = messageText,
            onValueChange = { messageText = it },
            onSend = { messageText = "" }
        )
    }
}

// ─── Top Bar ──────────────────────────────────────────────────────────────────
@Composable
fun ChatTopBar(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(TopBarBg)
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back button
        IconButton(onClick = onBack, modifier = Modifier.size(36.dp)) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = TextPrimary,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(6.dp))

        // Avatar
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(Color(0xFF1E3A5F)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color(0xFF60A5FA),
                modifier = Modifier.size(26.dp)
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Name + car info
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "John Doe",
                color = TextPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Toyota Camry • Silver (2022)",
                color = TextSecondary,
                fontSize = 11.sp
            )
        }

        // Call icon
        IconButton(onClick = {}, modifier = Modifier.size(36.dp)) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "Call",
                tint = TextPrimary,
                modifier = Modifier.size(20.dp)
            )
        }

        // Info icon
        IconButton(onClick = {}, modifier = Modifier.size(36.dp)) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                tint = TextPrimary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

// ─── Date Label ───────────────────────────────────────────────────────────────
@Composable
fun DateLabel(text: String) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(DateLabelBg)
                .padding(horizontal = 14.dp, vertical = 5.dp)
        ) {
            Text(
                text = text,
                color = TextSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
            )
        }
    }
}

// ─── Text Message Bubble ──────────────────────────────────────────────────────
@Composable
fun TextMessageBubble(message: ChatMessage.Text) {
    val isSent = message.isSent

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isSent) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        // Receiver avatar (left side)
        if (!isSent) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF1E3A5F)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color(0xFF60A5FA),
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        Column(
            horizontalAlignment = if (isSent) Alignment.End else Alignment.Start,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 18.dp,
                            topEnd = 18.dp,
                            bottomStart = if (isSent) 18.dp else 4.dp,
                            bottomEnd = if (isSent) 4.dp else 18.dp
                        )
                    )
                    .background(if (isSent) SentBubble else ReceivedBubble)
                    .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                Text(
                    text = message.text,
                    color = TextPrimary,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Time + read ticks
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (isSent) Arrangement.End else Arrangement.Start
            ) {
                Text(
                    text = message.time,
                    color = TextSecondary,
                    fontSize = 10.sp
                )
                if (isSent) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.DoneAll,
                        contentDescription = null,
                        tint = if (message.isRead) TickColor else TextSecondary,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }

        // Sender avatar (right side)
        if (isSent) {
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF1B3A5C)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = SentBubble,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

// ─── Image Message Bubble ─────────────────────────────────────────────────────
@Composable
fun ImageMessageBubble(message: ChatMessage.Image) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color(0xFF1E3A5F)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color(0xFF60A5FA),
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.widthIn(max = 280.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 18.dp,
                            topEnd = 18.dp,
                            bottomStart = 4.dp,
                            bottomEnd = 18.dp
                        )
                    )
                    .background(ReceivedBubble)
                    .padding(8.dp)
            ) {
                Column {
                    // Simulated dashboard image
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFF0A0A0A)),
                        contentAlignment = Alignment.Center
                    ) {
                        // Dark dashboard simulation
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFF0D1117))
                        )

                        // Speedometer circles
                        Box(
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF111827))
                                .align(Alignment.Center)
                        )
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF0F172A))
                                .align(Alignment.Center)
                        )

                        // Warning lights (yellow/red dots)
                        Row(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFBBF24)))
                            Box(modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFEF4444)))
                            Box(modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFBBF24)))
                        }

                        // Dashboard label
                        Text(
                            text = "Dashboard Lights",
                            color = TextSecondary.copy(alpha = 0.5f),
                            fontSize = 10.sp,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 6.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = message.caption,
                        color = TextPrimary,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = message.time,
                color = TextSecondary,
                fontSize = 10.sp
            )
        }
    }
}

// ─── Quick Replies Row ────────────────────────────────────────────────────────
@Composable
fun QuickRepliesRow(replies: List<String>, onReply: (String) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(InputBarBg)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 2.dp)
    ) {
        items(replies) { reply ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(QuickReplyBg)
                    .then(
                        Modifier.background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(20.dp)
                        )
                    )
                    .padding(1.dp)
            ) {
                TextButton(
                    onClick = { onReply(reply) },
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(QuickReplyBg),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = reply,
                        color = Color(0xFF93C5FD),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

// ─── Chat Input Bar ───────────────────────────────────────────────────────────
@Composable
fun ChatInputBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(InputBarBg)
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Plus / attach button
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(Color(0xFF1E2535))
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Attach",
                tint = TextSecondary,
                modifier = Modifier.size(20.dp)
            )
        }

        // Text field
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(24.dp))
                .background(InputFieldBg)
                .padding(horizontal = 14.dp, vertical = 4.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (value.isEmpty()) {
                Text(
                    text = "Type a message...",
                    color = TextSecondary,
                    fontSize = 14.sp
                )
            }
            BasicTextField(value = value, onValueChange = onValueChange)
        }

        // Camera icon
        IconButton(onClick = {}, modifier = Modifier.size(34.dp)) {
            Icon(
                imageVector = Icons.Default.CameraAlt,
                contentDescription = "Camera",
                tint = TextSecondary,
                modifier = Modifier.size(20.dp)
            )
        }

        // Attachment icon
        IconButton(onClick = {}, modifier = Modifier.size(34.dp)) {
            Icon(
                imageVector = Icons.Default.AttachFile,
                contentDescription = "File",
                tint = TextSecondary,
                modifier = Modifier.size(20.dp)
            )
        }

        //Send button
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(SendBtnBg),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = onSend, modifier = Modifier.size(42.dp)) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

// ─── BasicTextField wrapper ───────────────────────────────────────────────────
@Composable
fun BasicTextField(value: String, onValueChange: (String) -> Unit) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            color = TextPrimary,
            fontSize = 14.sp
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

// ─── Preview ──────────────────────────────────────────────────────────────────
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatScreenPreview() {
    MaterialTheme {
        ChatScreen()
    }
}