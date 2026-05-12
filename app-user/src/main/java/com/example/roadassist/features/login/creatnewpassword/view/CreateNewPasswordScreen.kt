package com.example.roadassist.features.login.creatnewpassword.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.R
import com.example.roadassist.components.PrimaryButton
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.features.login.creatnewpassword.viewmodel.CreateNewPasswordViewModel
import com.example.roadassist.theme.RoadAssistTheme
import com.example.roadassist.theme.TextPrimary

@Composable
fun CreateNewPasswordScreen(
    onUpdateClick: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: CreateNewPasswordViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.showSuccessDialog) {
        PasswordUpdatedDialog(
            onExploreClick = {
                viewModel.onDialogDismissed()
                onUpdateClick()
            }
        )
    }
    Scaffold(
        topBar = {
            UserTopBar(
                "Create new password",
                onBackClick,
                Color.White,
                TextPrimary
            )
        },
        containerColor = Color(0xFFF5F5F5),
        bottomBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                PrimaryButton(
                    text = "Update",
                    onClick = { viewModel.onUpdateClick() }
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))



            Spacer(modifier = Modifier.height(24.dp))

            Image(
                painter = painterResource(id = R.drawable.resetpassword),
                contentDescription = "New Password",
                modifier = Modifier.size(160.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Enter new password",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A2E)
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = uiState.newPassword,
                onValueChange = viewModel::onNewPasswordChange,
                placeholder = { Text("New password", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                isError = uiState.newPasswordError != null,
                supportingText = uiState.newPasswordError?.let {
                    { Text(it, color = MaterialTheme.colorScheme.error) }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = uiState.confirmPassword,
                onValueChange = viewModel::onConfirmPasswordChange,
                placeholder = { Text("Confirm password", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                isError = uiState.confirmPasswordError != null,
                supportingText = uiState.confirmPasswordError?.let {
                    { Text(it, color = MaterialTheme.colorScheme.error) }
                }
            )

        }

    }

}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CreateNewPasswordScreenPreview() {
    RoadAssistTheme {
        CreateNewPasswordScreen({}, {})
    }
}