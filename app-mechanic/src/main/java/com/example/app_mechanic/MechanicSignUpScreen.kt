package com.example.app_mechanic

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Color Palette

//Screen
@Composable
fun SignUpScreen(
    onBackClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onSignUpClick: (String, String, String, String, Boolean) -> Unit = { _, _, _, _, _ -> },
    onGoogleClick: () -> Unit = {},
    onGmailClick: () -> Unit = {}
) {

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var termsAccepted by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //Top Space
            Spacer(modifier = Modifier.height(16.dp))

            //Top Bar
            TopBar(onBackClick = onBackClick)

            Spacer(modifier = Modifier.height(32.dp))

            //Icon
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0xFF4A80F0),
                                Color(0xFF2E5CC8)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(34.dp)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            //Title
            Text(
                text = "Mechanic Sign Up",
                color = TextPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            //Subtitle
            Text(
                text = "Create your mechanic account to receive roadside requests",
                color = TextSecondary,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(28.dp))

            //Full Name
            FieldLabel("FULL NAME")

            Spacer(modifier = Modifier.height(8.dp))

            AppTextField(
                value = fullName,
                onValueChange = { fullName = it },
                placeholder = "John Doe",
                leadingIcon = Icons.Default.Person,
                keyboardType = KeyboardType.Text
            )

            Spacer(modifier = Modifier.height(18.dp))

            //Email
            FieldLabel("EMAIL ADDRESS")

            Spacer(modifier = Modifier.height(8.dp))

            AppTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = "john@example.com",
                leadingIcon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(18.dp))

            //Phone
            FieldLabel("PHONE NUMBER")

            Spacer(modifier = Modifier.height(8.dp))

            AppTextField(
                value = phone,
                onValueChange = { phone = it },
                placeholder = "+1 (555) 000-0000",
                leadingIcon = Icons.Default.Phone,
                keyboardType = KeyboardType.Phone
            )

            Spacer(modifier = Modifier.height(18.dp))

            //Password
            FieldLabel("PASSWORD")

            Spacer(modifier = Modifier.height(8.dp))

            AppTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "••••••••",
                leadingIcon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password,
                isPassword = true,
                passwordVisible = passwordVisible,
                onTogglePassword = {
                    passwordVisible = !passwordVisible
                }
            )

            Spacer(modifier = Modifier.height(22.dp))

            //Terms
            TermsRow(
                checked = termsAccepted,
                onCheckedChange = {
                    termsAccepted = it
                }
            )

            Spacer(modifier = Modifier.height(28.dp))

            //Create Account Button
            Button(
                onClick = {
                    onSignUpClick(
                        fullName,
                        email,
                        phone,
                        password,
                        termsAccepted
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AccentBlue,
                    contentColor = Color.White
                )
            ) {

                Text(
                    text = "Create Mechanic Account",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            //Divider
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = DividerColor
                )

                Text(
                    text = "  OR SIGN UP WITH  ",
                    color = TextSecondary,
                    fontSize = 12.sp
                )

                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = DividerColor
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Social Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                SocialButton(
                    modifier = Modifier.weight(1f),
                    label = "Google",
                    emoji = "G",
                    onClick = onGoogleClick
                )

                SocialButton(
                    modifier = Modifier.weight(1f),
                    label = "Gmail",
                    emoji = "✉️",
                    onClick = onGmailClick
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            //Footer
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Already have an account? ",
                    color = TextSecondary,
                    fontSize = 14.sp
                )

                Text(
                    text = "Log In",
                    color = AccentBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        onLoginClick()
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

//Top Bar
@Composable
private fun TopBar(onBackClick: () -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(SurfaceVariant)
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = TextPrimary
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            Text(
                text = "Login",
                color = TextSecondary,
                fontSize = 15.sp
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "Sign Up",
                    color = AccentBlue,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(2.dp)
                        .background(
                            AccentBlue,
                            RoundedCornerShape(2.dp)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.width(40.dp))
    }
}

//Label
@Composable
private fun FieldLabel(text: String) {

    Box(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = text,
            color = TextSecondary,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp
        )
    }
}

//TextField
@Composable
private fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector,
    keyboardType: KeyboardType,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePassword: (() -> Unit)? = null
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                color = TextHint
            )
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = IconColor
            )
        },
        trailingIcon =
            if (isPassword && onTogglePassword != null) {
                {
                    IconButton(onClick = onTogglePassword) {

                        Icon(
                            imageVector =
                                if (passwordVisible)
                                    Icons.Default.Visibility
                                else
                                    Icons.Default.VisibilityOff,
                            contentDescription = null,
                            tint = IconColor
                        )
                    }
                }
            } else null,
        singleLine = true,
        visualTransformation =
            if (isPassword && !passwordVisible)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AccentBlue,
            unfocusedBorderColor = BorderColor,
            focusedContainerColor = SurfaceDark,
            unfocusedContainerColor = SurfaceDark,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,
            cursorColor = AccentBlue
        )
    )
}

//Terms Row
@Composable
private fun TermsRow(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = CheckboxChecked,
                uncheckedColor = BorderColor
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            buildAnnotatedString {

                withStyle(
                    SpanStyle(color = TextSecondary)
                ) {
                    append("By creating an account, you agree to the ")
                }

                withStyle(
                    SpanStyle(
                        color = AccentBlue,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Terms of Service")
                }

                withStyle(
                    SpanStyle(color = TextSecondary)
                ) {
                    append(" and ")
                }

                withStyle(
                    SpanStyle(
                        color = AccentBlue,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Privacy Policy")
                }
            },
            fontSize = 13.sp
        )
    }
}

//Social Button
@Composable
private fun SocialButton(
    modifier: Modifier = Modifier,
    label: String,
    emoji: String,
    onClick: () -> Unit
) {

    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(54.dp),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, BorderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = SocialBg,
            contentColor = TextPrimary
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
                fontSize = 14.sp,
                color = TextPrimary
            )
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFF0F1117,
    device = "id:pixel_7"
)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}