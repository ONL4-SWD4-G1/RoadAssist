package com.example.app_admin.technicians.view.component.technicianDeduction

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_admin.R
import com.example.app_admin.complaints.view.component.ComplaintActionButton
import com.example.app_admin.theme.DangerRed
import com.example.app_admin.theme.DividerGray

@Composable
fun DeductionFooter(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = Color.White,
        shadowElevation = 16.dp,
        border = BorderStroke(width = 1.dp, color = DividerGray)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(width = 1.dp, color = DividerGray)
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
            }

            val buttonText = if (isLoading) {
                stringResource(R.string.saving)
            } else {
                stringResource(R.string.confirm_deduction)
            }

            ComplaintActionButton(
                text = buttonText,
                onClick = onConfirm,
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                containerColor = DangerRed,
                contentColor = Color.White,
                icon = Icons.Default.Block
            )
        }
    }
}