package com.example.app_admin.complaints.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_admin.R
import com.example.app_admin.complaints.view.component.refund.OrderFinancialSummaryCard
import com.example.app_admin.complaints.view.component.refund.RefundConfigCard
import com.example.app_admin.complaints.view.component.refund.RefundFooter
import com.example.app_admin.complaints.view.component.refund.RefundImpactPreview
import com.example.app_admin.complaints.view.component.refund.RefundReasonCard
import com.example.app_admin.complaints.viewModel.TechnicianRefundViewModel
import com.example.app_admin.shared.RoadAssistTopAppBar
import com.example.app_admin.theme.DarkNavy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechnicianRefundScreen(
    orderId: String,
    onBack: () -> Unit,
    viewModel: TechnicianRefundViewModel = viewModel()
) {
    LaunchedEffect(orderId) {
        viewModel.initialize(orderId)
    }

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            RoadAssistTopAppBar(
                title = stringResource(R.string.refund),
                containerColor = DarkNavy,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        },
        bottomBar = {
            RefundFooter(
                onConfirm = {
                    viewModel.confirmRefund(onSuccess = onBack)
                },
                onCancel = onBack,
                isLoading = uiState.isLoading
            )
        },
        containerColor = Color(0xFFF8F7F6)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OrderFinancialSummaryCard(uiState)

            RefundConfigCard(
                uiState = uiState,
                onTypeChange = { viewModel.setRefundType(it) },
                onAmountChange = { viewModel.updateAmount(it) }
            )

            RefundReasonCard(
                selectedReason = uiState.refundReason,
                onReasonChange = { viewModel.updateReason(it) },
                notes = uiState.adminNotes,
                onNotesChange = { viewModel.updateNotes(it) }
            )

            RefundImpactPreview(uiState)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TechnicianRefundScreenPreview() {
    TechnicianRefundScreen(
        orderId = "12345",
        onBack = {},
        viewModel = viewModel()
    )
}