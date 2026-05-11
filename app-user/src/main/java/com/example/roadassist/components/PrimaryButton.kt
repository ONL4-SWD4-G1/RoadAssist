package com.example.roadassist.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roadassist.theme.OrangeButton
import com.example.roadassist.theme.White


@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = OrangeButton,
    textColor: Color = White,
    iconTint: Color = White,
    icon: ImageVector? = null,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(16),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
    ) {
        icon?.let { Icon(icon, null, tint = iconTint, modifier = Modifier.size(20.dp)) }
        Spacer(Modifier.width(8.dp))
        Text(text = text, color = textColor, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
    }
}
