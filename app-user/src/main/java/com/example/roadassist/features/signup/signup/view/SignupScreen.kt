package com.example.roadassist.features.signup.signup.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.R
import com.example.roadassist.features.signup.signup.model.SignupUiState
import com.example.roadassist.features.signup.signup.viewmodel.SignupViewModel
import com.example.roadassist.theme.OrangeAccent

@Composable
fun SignupScreen(
    onSignupSuccess: () -> Unit,
    onLoginClick: () -> Unit,
    viewModel: SignupViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    SignupContent(
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onMobileChange = viewModel::onMobileChange,
        onPasswordChange = viewModel::onPasswordChange,
        onTabSelected = viewModel::onTabSelected,
        onSignupClick = { if (viewModel.validate()) onSignupSuccess() },
        onLoginTabClick = onLoginClick,
    )
}

@Composable
private fun SignupContent(
    uiState: SignupUiState,
    onNameChange: (String) -> Unit,
    onMobileChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTabSelected: (Int) -> Unit,
    onSignupClick: () -> Unit,
    onLoginTabClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Signup",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1A2E)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.signup),
            contentDescription = "Signup Image",
            modifier = Modifier.size(140.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        SignupLoginTabs(
            selectedTab = uiState.selectedTab,
            onTabSelected = onTabSelected,
            onLoginClick = onLoginTabClick
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Name Field
        OutlinedTextField(
            value = uiState.name,
            onValueChange = onNameChange,
            placeholder = { Text("Name", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            isError = uiState.nameError != null,
            supportingText = uiState.nameError?.let {
                {
                    Text(
                        it,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Mobile Field
        OutlinedTextField(
            value = uiState.mobileNumber,
            onValueChange = onMobileChange,
            placeholder = { Text("Mobile Number", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            isError = uiState.mobileError != null,
            supportingText = uiState.mobileError?.let {
                {
                    Text(
                        it,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Password Field
        OutlinedTextField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            placeholder = { Text("Create password", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            isError = uiState.passwordError != null,
            supportingText = uiState.passwordError?.let {
                {
                    Text(
                        it,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSignupClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = OrangeAccent)
        ) {
            Text(
                text = "Signup",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        SocialLoginButtons()

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun SocialLoginButtons() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .weight(1f)
                .height(46.dp),
            shape = RoundedCornerShape(30.dp),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google",
                modifier = Modifier.size(18.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Google", color = Color.DarkGray, fontSize = 13.sp)
        }

        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .weight(1f)
                .height(46.dp),
            shape = RoundedCornerShape(30.dp),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = "Facebook",
                modifier = Modifier.size(18.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Facebook", color = Color.DarkGray, fontSize = 13.sp)
        }
    }
}

@Composable
private fun SignupLoginTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    onLoginClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(30.dp))
            .padding(4.dp)
    ) {
        Button(
            onClick = { onTabSelected(0) },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            shape = RoundedCornerShape(26.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedTab == 0) OrangeAccent else Color.Transparent,
                contentColor = if (selectedTab == 0) Color.White else Color.Gray
            ),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            Text(text = "Signup", fontWeight = FontWeight.SemiBold)
        }

        Button(
            onClick = { onTabSelected(1); onLoginClick() },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            shape = RoundedCornerShape(26.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedTab == 1) OrangeAccent else Color.Transparent,
                contentColor = if (selectedTab == 1) Color.White else Color.Gray
            ),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            Text(text = "Login", fontWeight = FontWeight.SemiBold)
        }
    }
}