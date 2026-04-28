package com.example.roadassist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roadassist.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(onVerifySuccess: () -> Unit, onBackClick    : () -> Unit) {

    var otp1 by remember { mutableStateOf("") }
    var otp2 by remember { mutableStateOf("") }
    var otp3 by remember { mutableStateOf("") }
    var otp4 by remember { mutableStateOf("") }

    var timerSeconds by remember { mutableStateOf(30) }
    var isTimerRunning by remember { mutableStateOf(true) }

    LaunchedEffect(isTimerRunning) {
        if (isTimerRunning) {
            while (timerSeconds > 0) {
                delay(1000)
                timerSeconds--
            }
            isTimerRunning = false
        }
    }

    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }
    val focusRequester4 = remember { FocusRequester() }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }

            Text(
                text = "Verify Mobile number",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.size(48.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.otp),
            contentDescription = "OTP Image",
            modifier = Modifier.size(160.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Enter OTP",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OtpBox(
                value = otp1,
                focusRequester = focusRequester1,
                onValueChange = {
                    otp1 = it
                    if (it.isNotEmpty()) focusRequester2.requestFocus()
                }
            )

            OtpBox(
                value = otp2,
                focusRequester = focusRequester2,
                onValueChange = {
                    otp2 = it
                    if (it.isNotEmpty()) focusRequester3.requestFocus()
                }
            )

            OtpBox(
                value = otp3,
                focusRequester = focusRequester3,
                onValueChange = {
                    otp3 = it
                    if (it.isNotEmpty()) focusRequester4.requestFocus()
                }
            )

            OtpBox(
                value = otp4,
                focusRequester = focusRequester4,
                onValueChange = { otp4 = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = {
                    timerSeconds = 30
                    isTimerRunning = true
                },
                enabled = !isTimerRunning
            ) {
                Text(
                    text = "Resend OTP",
                    color = if (!isTimerRunning) OrangeAccent else Color.Gray
                )
            }

            Text(
                text = "00:${timerSeconds.toString().padStart(2, '0')}",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onVerifySuccess() },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = OrangeAccent)
        ) {
            Text(
                text = "Verify",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun OtpBox(value : String, focusRequester : FocusRequester, onValueChange : (String) -> Unit) {

    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= 1) onValueChange(it)
        },
        modifier = Modifier.size(58.dp).focusRequester(focusRequester),
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = OrangeAccent,
            unfocusedBorderColor = Color.LightGray
        )
    )
}