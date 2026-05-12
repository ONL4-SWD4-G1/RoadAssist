package com.example.roadassist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.roadassist.theme.TextSecondary
import com.example.roadassist.theme.White


@Composable
fun SuccessDialog(
    message: String,
    description: String = "",
    buttonText: String,
    onButtonClick: () -> Unit,
    icon: @Composable () -> Unit = { SuccessIcon() },
) {
    Dialog(onDismissRequest = {}) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = White),
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                icon()
                Spacer(Modifier.height(24.dp))
                Text(
                    text = message,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
                Spacer(Modifier.height(8.dp))
                if (description.isNotEmpty()) {
                    Text(description, color = TextSecondary, textAlign = TextAlign.Center)
                }
                Spacer(Modifier.height(24.dp))

                PrimaryButton(
                    text = buttonText,
                    onClick = onButtonClick,
                    modifier = Modifier.clip(RoundedCornerShape(32.dp))
                )
            }
        }
    }
}

