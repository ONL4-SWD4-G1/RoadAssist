package com.example.roadassist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roadassist.theme.NavyBlue
import com.example.roadassist.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTopBar(
    title: String,
    onNavigateBack: (() -> Unit)? = null,
    topAppBarColor: Color = NavyBlue,
    textColor: Color = White
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        },
        navigationIcon = {
            if (onNavigateBack != null) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = textColor,
                        modifier = Modifier.background(Color.Transparent)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = topAppBarColor),
        modifier = Modifier.clip(RoundedCornerShape(bottomStart = 18.dp, bottomEnd = 18.dp)),

        )
}