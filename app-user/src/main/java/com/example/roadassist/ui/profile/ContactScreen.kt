package com.example.roadassist.ui.profile

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.PhoneInTalk
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roadassist.R
import com.example.roadassist.ui.common.UserTopBar
import com.example.roadassist.ui.fakedata.Contact
import com.example.roadassist.ui.theme.DividerColor
import com.example.roadassist.ui.theme.OrangeButton
import com.example.roadassist.ui.theme.TextSecondary

@Composable
fun ContactScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = { UserTopBar("Contact", onNavigateBack) },
        containerColor = Color.White,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(24.dp))

            Image(
                painter = painterResource(R.drawable.contact),
                contentDescription = "",
                modifier = Modifier.size(185.dp),
                contentScale = ContentScale.Crop

            )
            Spacer(Modifier.height(32.dp))

            ContactSection(
                title = "Emergency contact",
                items = listOf(
                    Pair(Icons.Filled.PhoneInTalk, Contact.PHONE),
                    Pair(Icons.Filled.ChatBubbleOutline, "Chat with us"),
                ),
            )

            Spacer(Modifier.height(20.dp))

            ContactSection(
                title = "Write us",
                items = listOf(
                    Pair(Icons.Filled.Email, Contact.EMAIL),
                    Pair(Icons.Filled.NearMe, Contact.ADDRESS),
                ),
            )
        }
    }
}

@Composable
private fun ContactSection(title: String, items: List<Pair<ImageVector, String>>) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(12.dp))
        items.forEach { (icon, text) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(icon, null, tint = OrangeButton, modifier = Modifier.size(24.dp))
                Spacer(Modifier.width(16.dp))
                Text(text, fontSize = 16.sp, color = TextSecondary)
            }
            HorizontalDivider(color = DividerColor)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ContactScreenPreview() {
    ContactScreen { }
}