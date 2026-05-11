package com.example.roadassist.features.login.resetpassword.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.R
import com.example.roadassist.features.login.resetpassword.model.ResetPasswordUiState
import com.example.roadassist.features.login.resetpassword.viewmodel.ResetPasswordViewModel
import com.example.roadassist.theme.OrangeAccent
import com.example.roadassist.theme.TextGray

@Composable
fun ResetPasswordScreen(
    onGetOtpClick: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: ResetPasswordViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ResetPasswordContent(
        uiState = uiState,
        onMobileNumberChange = viewModel::onMobileNumberChange,
        onGetOtpClick = { if (viewModel.validate()) onGetOtpClick() },
        onBackClick = onBackClick,
    )
}

@Composable
private fun ResetPasswordContent(
    uiState: ResetPasswordUiState,
    onMobileNumberChange: (String) -> Unit,
    onGetOtpClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        ResetPasswordTopBar(
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.resetpassword),
            contentDescription = "Reset Password",
            modifier = Modifier.size(160.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Forgot your password?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1A2E)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "You can reset your password",
            fontSize = 14.sp,
            color = TextGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = uiState.mobileNumber,
            onValueChange = onMobileNumberChange,
            placeholder = { Text("Enter Mobile Number", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            isError = uiState.mobileError != null,
            supportingText = uiState.mobileError?.let {
                { Text(it, color = MaterialTheme.colorScheme.error) }
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onGetOtpClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = OrangeAccent)
        ) {
            Text(
                text = "Get OTP",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun ResetPasswordTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
        }
        Text(
            text = "Reset Password",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(48.dp))
    }
}