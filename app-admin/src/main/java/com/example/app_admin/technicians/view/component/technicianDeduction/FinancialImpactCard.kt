package com.example.app_admin.technicians.view.component.technicianDeduction

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.R
import com.example.app_admin.technicians.viewModel.TechnicianDeductionUiState
import com.example.app_admin.theme.BorderGray
import com.example.app_admin.theme.ColorNegative
import com.example.app_admin.theme.DarkNavy
import com.example.app_admin.theme.DividerGray
import com.example.app_admin.theme.LightText
import com.example.app_admin.theme.SuccessGreen


@Composable
fun FinancialImpactCard(
    state: TechnicianDeductionUiState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, DividerGray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HeaderSection()

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ImpactRow(
                    label = stringResource(R.string.current_pending_balance),
                    value = "${state.currentPendingBalance} ر.س",
                    valueColor = LightText
                )

                ImpactRow(
                    label = stringResource(R.string.deducted_amount),
                    value = "- ${state.deductionAmount} ر.س",
                    valueColor = ColorNegative
                )

                HorizontalDivider(
                    color = BorderGray,
                    thickness = 1.dp
                )

                ImpactRow(
                    label = stringResource(R.string.new_expected_balance),
                    value = "${state.expectedBalance} ر.س",
                    valueColor = SuccessGreen,
                    isBold = true
                )
            }
        }
    }
}

@Composable
private fun HeaderSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Payments,
            contentDescription = null,
            tint = DarkNavy,
            modifier = Modifier.size(18.dp)
        )

        Text(
            text = stringResource(R.string.financial_impact_preview),
            color = DarkNavy,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun ImpactRow(
    label: String,
    value: String,
    valueColor: Color,
    modifier: Modifier = Modifier,
    isBold: Boolean = false
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = LightText,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp)
        )

        Text(
            text = value,
            color = valueColor,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.sp,
                fontWeight = if (isBold) FontWeight.Bold else FontWeight.Medium
            )
        )
    }
}