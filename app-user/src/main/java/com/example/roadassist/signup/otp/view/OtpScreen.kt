package com.example.roadassist.signup.otp.view

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.R
import com.example.roadassist.signup.otp.model.OtpUiState
import com.example.roadassist.signup.otp.vm.OtpViewModel
import com.example.roadassist.ui.theme.OrangeAccent

@Composable
fun OtpScreen(
    onVerifySuccess: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: OtpViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Navigation Handler
    LaunchedEffect(uiState.navigateToHome) {
        if (uiState.navigateToHome) {
            viewModel.onNavigationHandled()
            onVerifySuccess()
        }
    }

    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }
    val focusRequester4 = remember { FocusRequester() }

    OtpContent(
        uiState = uiState,
        focusRequesters = listOf(focusRequester1, focusRequester2, focusRequester3, focusRequester4),
        onOtpChange = listOf(
            { value: String ->
                viewModel.onOtp1Change(value)
                if (value.isNotEmpty()) focusRequester2.requestFocus()
            },
            { value: String ->
                viewModel.onOtp2Change(value)
                if (value.isNotEmpty()) focusRequester3.requestFocus()
            },
            { value: String ->
                viewModel.onOtp3Change(value)
                if (value.isNotEmpty()) focusRequester4.requestFocus()
            },
            { value: String -> viewModel.onOtp4Change(value) }
        ),
        onResendOtp = viewModel::onResendOtp,
        onVerifyClick = viewModel::onVerifyClick,
        onBackClick = onBackClick
    )
}

@Composable
private fun OtpContent(
    uiState: OtpUiState,
    focusRequesters: List<FocusRequester>,
    onOtpChange: List<(String) -> Unit>,
    onResendOtp: () -> Unit,
    onVerifyClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val otpValues = listOf(uiState.otp1, uiState.otp2, uiState.otp3, uiState.otp4)

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        OtpTopBar(onBackClick = onBackClick)

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.otp),
            contentDescription = "OTP Image",
            modifier = Modifier.size(160.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Enter OTP", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            otpValues.forEachIndexed { index, value ->
                OtpBox(
                    value = value,
                    focusRequester = focusRequesters[index],
                    onValueChange = onOtpChange[index]
                )
            }
        }

        // Error Message
        uiState.otpError?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OtpTimerRow(
            formattedTimer = uiState.formattedTimer,
            isTimerRunning = uiState.isTimerRunning,
            onResendOtp = onResendOtp
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onVerifyClick,
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
private fun OtpTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
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
}

@Composable
private fun OtpTimerRow(
    formattedTimer: String,
    isTimerRunning: Boolean,
    onResendOtp: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = onResendOtp, enabled = !isTimerRunning) {
            Text(
                text = "Resend OTP",
                color = if (!isTimerRunning) OrangeAccent else Color.Gray
            )
        }
        Text(text = formattedTimer, color = Color.Gray, fontSize = 14.sp)
    }
}

@Composable
private fun OtpBox(
    value: String,
    focusRequester: FocusRequester,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { if (it.length <= 1) onValueChange(it) },
        modifier = Modifier.size(58.dp).focusRequester(focusRequester),
        textStyle = TextStyle(
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