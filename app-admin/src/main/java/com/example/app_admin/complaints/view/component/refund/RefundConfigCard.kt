package com.example.app_admin.complaints.view.component.refund

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.R
import com.example.app_admin.complaints.viewModel.RefundUiState
import com.example.app_admin.theme.BackgroundGray
import com.example.app_admin.theme.DarkNavy
import com.example.app_admin.theme.DividerGray
import com.example.app_admin.theme.LightBlueGray
import com.example.app_admin.theme.LightText
import com.example.app_admin.theme.PrimaryOrange

@Composable
fun RefundConfigCard(
    uiState: RefundUiState,
    onTypeChange: (Boolean) -> Unit,
    onAmountChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, DividerGray),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(R.string.refund_type),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = DarkNavy
                )
                Spacer(Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.Tune,
                    contentDescription = null,
                    tint = PrimaryOrange,
                    modifier = Modifier.size(18.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RefundTypeSelector(
                    title = stringResource(R.string.partial_refund),
                    subtitle = stringResource(R.string.select_amount),
                    isSelected = !uiState.isFullRefund,
                    modifier = Modifier.weight(1f),
                    onClick = { onTypeChange(false) }
                )

                RefundTypeSelector(
                    title = stringResource(R.string.full_refund),
                    subtitle = "${uiState.totalPaid} ر.س",
                    isSelected = uiState.isFullRefund,
                    modifier = Modifier.weight(1f),
                    onClick = { onTypeChange(true) }
                )
            }

            AnimatedVisibility(visible = !uiState.isFullRefund) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(R.string.requested_refund_amount),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = LightText
                    )
                    OutlinedTextField(
                        value = uiState.refundAmount,
                        onValueChange = onAmountChange,
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = LocalTextStyle.current.copy(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End
                        ),
                        trailingIcon = {
                            Text(
                                text = "ر.س",
                                modifier = Modifier.padding(end = 12.dp),
                                color = LightBlueGray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryOrange,
                            unfocusedContainerColor = BackgroundGray
                        )
                    )
                    Text(
                        text = stringResource(R.string.refund_amount_exceeds_limit, uiState.totalPaid),
                        fontSize = 10.sp,
                        color = LightBlueGray
                    )
                }
            }
        }
    }
}

@Composable
private fun RefundTypeSelector(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) PrimaryOrange else DividerGray
    val bgColor = if (isSelected) PrimaryOrange.copy(alpha = 0.05f) else Color.Transparent

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RadioButton(
                selected = isSelected,
                onClick = null,
                colors = RadioButtonDefaults.colors(selectedColor = PrimaryOrange)
            )

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color(0xFF0F172A)
                )
                Text(
                    text = subtitle,
                    fontSize = 10.sp,
                    color = LightText
                )
            }
        }
    }
}