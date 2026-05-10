package com.example.app_admin.technicians.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_admin.R
import com.example.app_admin.complaints.view.component.ComplaintActionButton
import com.example.app_admin.shared.RoadAssistTopAppBar
import com.example.app_admin.technicians.view.component.DecisionImpactCard
import com.example.app_admin.technicians.view.component.SuspensionReasonDropdown
import com.example.app_admin.technicians.view.component.TechMiniProfileCard
import com.example.app_admin.technicians.viewModel.TechnicianSuspensionViewModel
import com.example.app_admin.theme.BackgroundGray
import com.example.app_admin.theme.BorderGray
import com.example.app_admin.theme.DangerRed
import com.example.app_admin.theme.DarkNavy
import com.example.app_admin.theme.DividerGray
import com.example.app_admin.theme.PrimaryOrange
import com.example.app_admin.theme.SoftGray
import com.example.app_admin.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechnicianSuspensionScreen(
    techId: Int,
    onBack: () -> Unit,
    viewModel: TechnicianSuspensionViewModel = viewModel()
) {
    LaunchedEffect(techId) {
        viewModel.initialize(techId)
    }
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            RoadAssistTopAppBar(
                title = stringResource(R.string.technician_suspension),
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
            SuspensionFooter(
                onConfirm = { viewModel.confirmSuspension() },
                onCancel = onBack
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
            TechMiniProfileCard(
                name = uiState.techName,
                specialty = uiState.techSpecialty,
                jobs = uiState.completedJobs,
                rating = uiState.rating
            )

            SuspensionSettingsCard(
                isPermanent = uiState.isPermanent,
                selectedDuration = uiState.selectedDuration,
                onTypeChange = { viewModel.updateSuspensionType(it) },
                onDurationChange = { viewModel.updateDuration(it) },
                adminNote = uiState.adminNote,
                onNoteChange = { viewModel.updateNote(it) },
                selectedReason = uiState.selectedReason,
                onReasonChange = { viewModel.updateReason(it) }
            )

            DecisionImpactCard(count = uiState.activeOrdersCount)
        }
    }
}


@Composable
fun SuspensionSettingsCard(
    isPermanent: Boolean,
    selectedDuration: String,
    onTypeChange: (Boolean) -> Unit,
    onDurationChange: (String) -> Unit,
    adminNote: String,
    onNoteChange: (String) -> Unit,
    selectedReason: String,
    onReasonChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, DividerGray)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SectionHeader(
                title = stringResource(R.string.suspension_type),
                icon = Icons.Default.Settings
            )
            SuspensionTypeSelector(isPermanent = isPermanent, onTypeChange = onTypeChange)

            if (!isPermanent) {
                DurationSelector(
                    selectedDuration = selectedDuration,
                    onDurationChange = onDurationChange
                )
            }

            ReasonSelector(
                selectedReason = selectedReason,
                onReasonChange = onReasonChange
            )
            AdminNoteField(
                adminNote = adminNote,
                onNoteChange = onNoteChange
            )
        }
    }
}

@Composable
private fun SuspensionTypeSelector(
    isPermanent: Boolean,
    onTypeChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DividerGray, RoundedCornerShape(8.dp))
            .padding(4.dp)
    ) {
        ToggleButton(
            text = stringResource(R.string.permanent_suspension),
            isSelected = isPermanent,
            modifier = Modifier.weight(1f),
            onClick = { onTypeChange(true) }
        )
        ToggleButton(
            text = stringResource(R.string.temporary_suspension),
            isSelected = !isPermanent,
            modifier = Modifier.weight(1f),
            onClick = { onTypeChange(false) }
        )
    }
}

@Composable
private fun DurationSelector(selectedDuration: String, onDurationChange: (String) -> Unit) {
    val durations = listOf(
        stringResource(R.string.three_days),
        stringResource(R.string.seven_days),
        stringResource(R.string.thirty_days),
        stringResource(R.string.custom)
    )
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = stringResource(R.string.suspension_duration),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            durations.forEach { duration ->
                DurationItem(
                    text = duration,
                    isSelected = selectedDuration == duration,
                    modifier = Modifier.weight(1f),
                    onClick = { onDurationChange(duration) }
                )
            }
        }
    }
}

@Composable
private fun ReasonSelector(
    selectedReason: String,
    onReasonChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = stringResource(R.string.suspension_reason),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        SuspensionReasonDropdown(
            selectedReason = selectedReason,
            onReasonChange = onReasonChange
        )
    }
}

@Composable
private fun AdminNoteField(
    adminNote: String,
    onNoteChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = stringResource(R.string.suspension_reason_required),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        OutlinedTextField(
            value = adminNote,
            onValueChange = onNoteChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            placeholder = {
                Text(
                    text = stringResource(R.string.additional_details_hint),
                    fontSize = 13.sp
                )
            },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = BackgroundGray
            )
        )
    }
}

@Composable
private fun SectionHeader(
    title: String,
    icon: ImageVector
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = PrimaryOrange,
            modifier = Modifier.size(16.dp)
        )
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

@Composable
fun ToggleButton(
    text: String,
    isSelected: Boolean,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(if (isSelected) DarkNavy else Color.Transparent)
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Gray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DurationItem(
    text: String,
    isSelected: Boolean,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .border(
                1.dp,
                if (isSelected) PrimaryOrange else BorderGray,
                RoundedCornerShape(8.dp)
            )
            .background(if (isSelected) PrimaryOrange.copy(0.1f) else Color.Transparent)
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) PrimaryOrange else TextGray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun SuspensionFooter(onConfirm: () -> Unit, onCancel: () -> Unit) {
    Surface(
        color = Color.White,
        shadowElevation = 10.dp,
        border = BorderStroke(1.dp, BorderGray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ComplaintActionButton(
                text = stringResource(R.string.confirm_account_suspension),
                onClick = onConfirm,
                containerColor = DangerRed,
                contentColor = Color.White,
                icon = Icons.Default.Block,
                isPrimary = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )
            TextButton(
                onClick = onCancel,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.process_cancel),
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TechnicianSuspensionScreenPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        TechnicianSuspensionScreen(
            techId = 1,
            onBack = {}
        )
    }
}