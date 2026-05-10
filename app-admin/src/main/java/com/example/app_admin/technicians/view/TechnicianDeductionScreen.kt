package com.example.app_admin.technicians.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.app_admin.shared.RoadAssistTopAppBar
import com.example.app_admin.technicians.view.component.technicianDeduction.DeductionFooter
import com.example.app_admin.technicians.view.component.technicianDeduction.DeductionFormCard
import com.example.app_admin.technicians.view.component.technicianDeduction.FinancialImpactCard
import com.example.app_admin.technicians.view.component.technicianDeduction.NotificationPreviewCard
import com.example.app_admin.technicians.view.component.technicianDeduction.TechnicianFinanceHeader
import com.example.app_admin.technicians.viewModel.TechnicianDeductionViewModel
import com.example.app_admin.theme.DarkNavy
import com.example.app_admin.theme.SoftGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechnicianDeductionScreen(
    techId: Int,
    onBack: () -> Unit,
    viewModel: TechnicianDeductionViewModel = viewModel()
) {
    LaunchedEffect(techId) {
        viewModel.initialize(techId)
    }

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            RoadAssistTopAppBar(
                title = stringResource(R.string.technician_deduction),
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
            DeductionFooter(
                onConfirm = { viewModel.confirmDeduction() },
                onCancel = onBack,
                isLoading = uiState.isLoading
            )
        },
        containerColor = SoftGray
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TechnicianFinanceHeader(uiState)

            DeductionFormCard(
                amount = uiState.deductionAmount,
                onAmountChange = { viewModel.updateAmount(it) },
                selectedReason = uiState.deductionReason,
                onReasonChange = { viewModel.updateReason(it) },
                adminNote = uiState.adminNote,
                onNoteChange = { viewModel.updateNote(it) }
            )

            FinancialImpactCard(uiState)

            NotificationPreviewCard(uiState)

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TechnicianDeductionScreenPreview() {
    TechnicianDeductionScreen(
        techId = 1,
        onBack = {}
    )
}