package com.example.roadassist.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.roadassist.ui.theme.NavyBlue
import com.example.roadassist.ui.theme.OrangeButton
import com.example.roadassist.ui.theme.White


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTopBar(
    title: String,
    onNavigateBack: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = White,
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
                        tint = White,
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue),
        modifier = Modifier.clip(RoundedCornerShape(bottomStart = 18.dp, bottomEnd = 18.dp)),

        )
}


@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(16),
        colors = ButtonDefaults.buttonColors(containerColor = OrangeButton),
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
    ) {
        Text(text = text, color = White, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
    }
}


@Composable
fun UnderlineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color(0xFF9CA3AF)) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = NavyBlue,
            unfocusedIndicatorColor = Color(0xFFD1D5DB),
        ),
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
    )
}


@Composable
fun RoundedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color(0xFF9CA3AF)) },
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = NavyBlue,
            unfocusedBorderColor = Color(0xFFD1D5DB),
        ),
        maxLines = maxLines,
        modifier = modifier.fillMaxWidth(),
    )
}


@Composable
fun SuccessDialog(
    message: String,
    buttonText: String,
    onButtonClick: () -> Unit,
    icon: @Composable () -> Unit = {},
) {
    Dialog(onDismissRequest = {}) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = White),
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                icon()
                Spacer(Modifier.height(16.dp))
                Text(
                    text = message,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                )
                Spacer(Modifier.height(24.dp))
                PrimaryButton(text = buttonText, onClick = onButtonClick)
            }
        }
    }
}

@Composable
fun SectionHeader(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = modifier.padding(bottom = 8.dp),
    )
}