package com.example.app_admin.complaints.view.component.refund

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.example.app_admin.complaints.viewModel.RefundUiState
import com.example.app_admin.theme.BackgroundGray
import com.example.app_admin.theme.DangerBg
import com.example.app_admin.theme.DangerRed
import com.example.app_admin.theme.PrimaryOrange
import com.example.app_admin.theme.SuccessBg
import com.example.app_admin.theme.SuccessGreen

@Composable
fun RefundImpactPreview(state: RefundUiState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Analytics,
                    contentDescription = null,
                    tint = PrimaryOrange,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = stringResource(R.string.financial_impact_preview),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            ImpactItem(
                label = stringResource(R.string.refund_to_customer_wallet),
                value = "+${state.clientReturn} ر.س",
                color = SuccessGreen,
                icon = Icons.Default.Person,
                iconBg = SuccessBg
            )

            HorizontalDivider(color = BackgroundGray)

            ImpactItem(
                label = stringResource(R.string.deduct_from_technician_including_commission),
                value = "-${state.techDeduction} ر.س",
                color = DangerRed,
                icon = Icons.Default.Engineering,
                iconBg = DangerBg
            )
        }
    }
}