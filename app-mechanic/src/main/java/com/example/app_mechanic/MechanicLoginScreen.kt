package com.example.roadassist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val BackgroundDark = Color(0xFF0F1117)
private val CardDark = Color(0xFF1E2235)
private val AccentBlue = Color(0xFF3D7BFF)
private val TextPrimary = Color(0xFFFFFFFF)
private val TextSecondary = Color(0xFF8A8FA8)
private val TextHint = Color(0xFF4A5068)
private val BorderColor = Color(0xFF2A2E45)
private val IconBg = Color(0xFF1C2540)
private val DividerColor = Color(0xFF252840)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onBackClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onLoginClick: (email: String, password: String) -> Unit = { _, _ -> },
    onForgotPassword: () -> Unit = {},
    onGoogleLogin: () -> Unit = {},
    onAppleLogin: () -> Unit = {},
    onCreateAccount: () -> Unit = {}
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPass by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //Top Bar
            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = TextPrimary
                    )
                }

                Spacer(Modifier.weight(1f))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Login",
                        color = AccentBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Spacer(Modifier.height(4.dp))

                    Box(
                        Modifier
                            .width(40.dp)
                            .height(2.dp)
                            .background(
                                AccentBlue,
                                RoundedCornerShape(1.dp)
                            )
                    )
                }

                Spacer(Modifier.width(24.dp))

                Text(
                    text = "Sign Up",
                    color = TextSecondary,
                    fontSize = 16.sp,
                    modifier = Modifier.clickable {
                        onSignUpClick()
                    }
                )

                Spacer(Modifier.weight(1f))
                Spacer(Modifier.width(48.dp))
            }

            Spacer(Modifier.height(32.dp))

            //Mechanic Icon
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(IconBg),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🔧",
                    fontSize = 32.sp
                )
            }

            Spacer(Modifier.height(20.dp))

            // Title
            Text(
                text = "Mechanic Login",
                color = TextPrimary,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Access your mechanic dashboard",
                color = TextSecondary,
                fontSize = 14.sp
            )

            Spacer(Modifier.height(28.dp))

            //Email Field
            FieldLabel(text = "EMAIL ADDRESS")

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Text(
                        "your@email.com",
                        color = TextHint,
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = null,
                        tint = TextSecondary
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = BorderColor,
                    focusedBorderColor = AccentBlue,
                    unfocusedContainerColor = CardDark,
                    focusedContainerColor = CardDark,
                    cursorColor = AccentBlue,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                )
            )

            Spacer(Modifier.height(16.dp))

            //Password Field
            FieldLabel(text = "PASSWORD")

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        "••••••••",
                        color = TextHint,
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = null,
                        tint = TextSecondary
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            showPass = !showPass
                        }
                    ) {
                        Icon(
                            imageVector =
                                if (showPass) Icons.Default.VisibilityOff
                                else Icons.Default.Visibility,
                            contentDescription = "Toggle password",
                            tint = TextSecondary
                        )
                    }
                },
                singleLine = true,
                visualTransformation =
                    if (showPass) VisualTransformation.None
                    else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = BorderColor,
                    focusedBorderColor = AccentBlue,
                    unfocusedContainerColor = CardDark,
                    focusedContainerColor = CardDark,
                    cursorColor = AccentBlue,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                )
            )

            //Forgot Password
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {

                TextButton(onClick = onForgotPassword) {
                    Text(
                        text = "Forgot password?",
                        color = AccentBlue,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            //Login Button
            Button(
                onClick = {
                    onLoginClick(email, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AccentBlue,
                    contentColor = TextPrimary
                )
            ) {

                Text(
                    text = "Login as Mechanic",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(28.dp))

            // Divider
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Divider(
                    modifier = Modifier.weight(1f),
                    color = DividerColor
                )

                Text(
                    text = "  OR LOGIN WITH  ",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    letterSpacing = 1.sp
                )

                Divider(
                    modifier = Modifier.weight(1f),
                    color = DividerColor
                )
            }

            Spacer(Modifier.height(20.dp))

            //Social Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                SocialButton(
                    label = "Google",
                    emoji = "G",
                    modifier = Modifier.weight(1f),
                    onClick = onGoogleLogin
                )

                SocialButton(
                    label = "Gmail",
                    emoji = "✉️",
                    modifier = Modifier.weight(1f),
                    onClick = onGoogleLogin
                )
            }

            //Create Account
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "\nNew mechanic here? ",
                    color = TextSecondary,
                    fontSize = 14.sp
                )

                Text(
                    text = "\nCreate Account",
                    color = AccentBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        onCreateAccount()
                    }
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

//Field Label
@Composable
private fun FieldLabel(text: String) {

    Box(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = text,
            color = TextSecondary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.2.sp
        )
    }
}

//Social Button
@Composable
private fun SocialButton(
    label: String,
    emoji: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(52.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = CardDark
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            BorderColor
        )
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = emoji,
                fontSize = 18.sp
            )

            Text(
                text = label,
                color = TextPrimary,
                fontSize = 14.sp
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF0F1117)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}