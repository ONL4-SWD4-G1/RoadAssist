package com.example.roadassist.features.profile.settings.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.R
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.features.profile.settings.viewmodel.SettingsViewModel
import com.example.roadassist.theme.IconTint
import com.example.roadassist.theme.NavyBlue

@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToChangePassword: () -> Unit,
    onNavigateToFeedback: () -> Unit,
    viewModel: SettingsViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UserTopBar("Settings", onNavigateBack) },
        containerColor = Color.White,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
        ) {
            Spacer(Modifier.height(16.dp))

            SettingsToggleRow(
                label = "Dark mode",
                checked = uiState.darkMode,
                onCheckedChange = { viewModel.onDarkModeToggled(it) },
            )
            SettingsToggleRow(
                label = "Notification",
                checked = uiState.notifications,
                onCheckedChange = { viewModel.onNotificationsToggled(it) },
            )

            HorizontalDivider(
                color = Color(0xFFE5E7EB),
                modifier = Modifier.padding(vertical = 12.dp)
            )

            SettingsNavRow("Change password", onNavigateToChangePassword)
            SettingsNavRow("Rate this app", onNavigateToFeedback)
        }
    }
}

@Composable
private fun SettingsToggleRow(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(label, fontSize = 16.sp, modifier = Modifier.weight(1f))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(checkedTrackColor = NavyBlue),
            thumbContent = if (checked) {
                {
                    Image(
                        painter = painterResource(R.drawable.check),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .scale(1.5f),
                    )
                }
            } else null,
        )
    }
}

@Composable
private fun SettingsNavRow(label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(label, fontSize = 16.sp, modifier = Modifier.weight(1f))
        Icon(Icons.Default.ChevronRight, "", tint = IconTint)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen({}, {}, {})
}
