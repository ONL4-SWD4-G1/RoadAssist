package com.example.app_admin.auth.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.R
import com.example.app_admin.theme.IstighathaColors
import kotlinx.coroutines.delay

object AuthLayout {
    const val LogoSizeDp: Int = 140
    val TopInset: Dp = 28.dp
    val LogoToTitle: Dp = 20.dp
    val LogoHorizontalInset: Dp = 32.dp
}

@Composable
fun IstighathaLogo(
    modifier: Modifier = Modifier,
    size: Int = AuthLayout.LogoSizeDp,
    contentDescription: String? = null,
) {
    Image(
        painter = painterResource(R.drawable.ic_auth_logo),
        contentDescription = contentDescription ?: stringResource(R.string.auth_logo_content_description),
        modifier = modifier
            .padding(horizontal = AuthLayout.LogoHorizontalInset)
            .size(size.dp),
        contentScale = ContentScale.Fit,
    )
}

@Composable
fun AuthBrandingHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(AuthLayout.TopInset))
        IstighathaLogo()
        Spacer(modifier = Modifier.height(AuthLayout.LogoToTitle))
    }
}

@Composable
fun AuthHeaderText(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 22.sp, lineHeight = 28.sp),
            color = IstighathaColors.Secondary,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp, lineHeight = 22.sp),
            color = IstighathaColors.OnSurfaceVariant,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}

@Composable
fun AuthInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Email,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onPasswordVisibilityToggle: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    focusedBorderColor: Color = IstighathaColors.PrimaryContainer,
    unfocusedBorderColor: Color = IstighathaColors.OutlineVariant,
    imeAction: ImeAction = ImeAction.Default,
) {
    val visualTransformation =
        if (isPassword && !isPasswordVisible) PasswordVisualTransformation()
        else VisualTransformation.None

    val fieldTextStyle = MaterialTheme.typography.bodyLarge.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium,
        color = IstighathaColors.OnSurface,
    )
    val placeholderTextStyle = fieldTextStyle.copy(
        fontWeight = FontWeight.Normal,
        color = IstighathaColors.OnSurfaceVariant,
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 58.dp),
        placeholder = {
            Text(
                text = placeholder,
                style = placeholderTextStyle,
            )
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = IstighathaColors.OnSurfaceVariant,
                modifier = Modifier.size(26.dp),
            )
        },
        trailingIcon = if (isPassword && onPasswordVisibilityToggle != null) {
            {
                IconButton(
                    onClick = onPasswordVisibilityToggle,
                    modifier = Modifier.size(48.dp),
                ) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Toggle password visibility",
                        tint = IstighathaColors.OnSurfaceVariant,
                        modifier = Modifier.size(22.dp),
                    )
                }
            }
        } else {
            null
        },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction,
        ),
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = unfocusedBorderColor,
            focusedContainerColor = IstighathaColors.Surface,
            unfocusedContainerColor = IstighathaColors.Surface,
            cursorColor = IstighathaColors.Primary,
            focusedTextColor = IstighathaColors.OnSurface,
            unfocusedTextColor = IstighathaColors.OnSurface,
            focusedLeadingIconColor = IstighathaColors.OnSurfaceVariant,
            unfocusedLeadingIconColor = IstighathaColors.OnSurfaceVariant,
        ),
        textStyle = fieldTextStyle,
    )
}

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    backgroundColor: Color = IstighathaColors.Secondary,
    textColor: Color = IstighathaColors.OnPrimary,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                clip = true
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(16.dp),
        enabled = enabled && !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = textColor,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 17.sp, lineHeight = 24.sp),
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SecondaryLinkButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = IstighathaColors.PrimaryContainer,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp, lineHeight = 22.sp),
        color = textColor,
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 12.dp)
    )
}

@Composable
fun OTPInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    count: Int = 4,
    borderColor: Color = IstighathaColors.PrimaryContainer,
    focusedBorderColor: Color = IstighathaColors.Primary,
) {
    val focusRequesters = remember(count) { List(count) { FocusRequester() } }
    val focusManager = LocalFocusManager.current

    fun applyCellChange(index: Int, newRaw: String) {
        val incoming = newRaw.filter { it.isDigit() }
        when {
            newRaw.isEmpty() -> {
                if (value.getOrNull(index) != null) {
                    onValueChange(value.take(index) + value.drop(index + 1))
                } else if (index > 0) {
                    focusRequesters[index - 1].requestFocus()
                    onValueChange(value.take(index - 1) + value.drop(index))
                }
            }

            incoming.length > 1 -> {
                onValueChange(incoming.take(count))
                if (incoming.length >= count) {
                    focusManager.clearFocus()
                } else {
                    focusRequesters[incoming.length.coerceAtMost(count - 1)].requestFocus()
                }
            }

            else -> {
                val digit = incoming.last()
                val next = (value.take(index) + digit + value.drop(index + 1)).take(count)
                onValueChange(next)
                if (index < count - 1) {
                    focusRequesters[index + 1].requestFocus()
                } else {
                    focusManager.clearFocus()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(32)
        val target = value.length.coerceIn(0, count - 1)
        focusRequesters[target].requestFocus()
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(count) { index ->
            var isFocused by remember(index) { mutableStateOf(false) }
            val cellValue = value.getOrNull(index)?.toString() ?: ""
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .border(
                        width = if (isFocused) 3.dp else 2.dp,
                        color = if (isFocused) focusedBorderColor else borderColor,
                        shape = RoundedCornerShape(12.dp),
                    )
                    .background(IstighathaColors.Surface, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center,
            ) {
                BasicTextField(
                    value = cellValue,
                    onValueChange = { applyCellChange(index, it) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp)
                        .focusRequester(focusRequesters[index])
                        .onFocusChanged { isFocused = it.isFocused },
                    textStyle = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 22.sp,
                        lineHeight = 28.sp,
                        color = IstighathaColors.Secondary,
                        textAlign = TextAlign.Center,
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    cursorBrush = SolidColor(IstighathaColors.Primary),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            innerTextField()
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun SuccessCheckMark(
    modifier: Modifier = Modifier,
    size: Int = 120
) {
    Box(
        modifier = modifier
            .size(size.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = "Success",
            tint = IstighathaColors.Success,
            modifier = Modifier.size((size * 0.6).dp)
        )
    }
}

@Composable
fun AuthSpacer(height: Int = 16) {
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun AuthBackgroundDecoration(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                color = IstighathaColors.Surface.copy(alpha = 0.3f),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
    )
}