package com.example.app_admin.complaints.view.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.theme.DarkNavy
import com.example.app_admin.theme.PrimaryOrange

@Composable
fun ComplaintActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isPrimary: Boolean = true,
    containerColor: Color = PrimaryOrange,
    contentColor: Color = if (isPrimary) Color.White else DarkNavy,
    borderColor: Color = Color(0xFFE2E8F0),
    icon: ImageVector? = null
) {
    if (isPrimary) {
        Button(
            onClick = onClick,
            modifier = modifier.height(if (icon != null) 48.dp else 36.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor
            ),
            shape = RoundedCornerShape(if (icon != null) 12.dp else 8.dp)
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(18.dp)
                )
            }
            Text(
                text = text,
                fontSize = if (icon != null) 14.sp else 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = if (icon != null) Modifier.padding(horizontal = 4.dp) else Modifier
            )
        }
    } else {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier.height(if (icon != null) 40.dp else 36.dp),
            border = BorderStroke(1.dp, borderColor),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(16.dp),
                    tint = contentColor
                )
            }
            Text(
                text = text,
                fontSize = 11.sp,
                color = contentColor
            )
        }
    }
}