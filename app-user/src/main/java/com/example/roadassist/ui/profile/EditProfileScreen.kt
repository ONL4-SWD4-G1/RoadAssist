package com.example.roadassist.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roadassist.ui.common.PrimaryButton
import com.example.roadassist.ui.common.UserTopBar
import com.example.roadassist.ui.fakedata.User
import com.example.roadassist.ui.theme.CardBg
import com.example.roadassist.ui.theme.IconTint
import com.example.roadassist.ui.theme.NavyBlue
import com.example.roadassist.ui.theme.OffWhite
import com.example.roadassist.ui.theme.TextSecondary


@Composable
fun EditProfileScreen(onNavigateBack: () -> Unit, onUpdated: () -> Unit) {
    var username by remember { mutableStateOf(User.NAME) }
    var phone by remember { mutableStateOf(User.PHONE) }

    Scaffold(
        topBar = { UserTopBar("Edit Profile", onNavigateBack) },
        containerColor = Color.White,
        bottomBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                PrimaryButton("Update", onUpdated)
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(16.dp))
            Box {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(CardBg)
                        .border(
                            border = BorderStroke(color = OffWhite, width = 2.dp),
                            shape = CircleShape
                        ),

                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        User.AVATAR,
                        null,
                        tint = NavyBlue,
                        modifier = Modifier.size(56.dp)

                    )
                }
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.BottomEnd)
                        .border(
                            border = BorderStroke(color = OffWhite, width = 1.dp),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        Icons.Outlined.CameraAlt,
                        null,
                        tint = IconTint,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Username", fontSize = 12.sp, color = TextSecondary)
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = NavyBlue,
                        unfocusedIndicatorColor = OffWhite,
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Spacer(Modifier.height(16.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Mobile Number", fontSize = 12.sp, color = TextSecondary)
                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = NavyBlue,
                        unfocusedIndicatorColor = OffWhite,
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Spacer(Modifier.height(80.dp))
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EditProfileScreenPreview() {

    EditProfileScreen(onNavigateBack = {}, onUpdated = {})
}