package com.example.roadassist.features.profile.userprofile.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.Discount
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.R
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.fakedata.User
import com.example.roadassist.features.profile.logout.LogoutDialog
import com.example.roadassist.features.profile.userprofile.viewmodel.ProfileViewModel
import com.example.roadassist.navigation.Routes
import com.example.roadassist.theme.DividerColor
import com.example.roadassist.theme.IconTint
import com.example.roadassist.theme.OffWhite
import com.example.roadassist.theme.TextPrimary
import com.example.roadassist.theme.TextSecondary

@Composable
fun ProfileScreen(
    onNavigateBack: () -> Unit,
    onNavigateTo: (String) -> Unit,
    onNavigateToEdit: () -> Unit,
    onLogout: () -> Unit,
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.showLogoutDialog) {
        LogoutDialog(
            onConfirm = {
                viewModel.onLogoutDismissed()
                onLogout()
            },
            onCancel = {
                viewModel.onLogoutDismissed()
            },
        )
    }

    val menuItems = listOf(
        Triple(Icons.Outlined.History, "Service history", Routes.SERVICE_HISTORY),
        Triple(Icons.Outlined.Discount, "My subscription", Routes.SUBSCRIPTION),
        Triple(Icons.Outlined.Info, "About us", Routes.ABOUT_US),
        Triple(Icons.Outlined.Phone, "Contact us", Routes.CONTACT),
        Triple(Icons.Outlined.Settings, "Settings", Routes.SETTINGS),
    )

    Scaffold(
        topBar = { UserTopBar("My Profile", onNavigateBack) },
        containerColor = Color.White,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .border(
                            border = BorderStroke(color = OffWhite, width = 2.dp),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(R.drawable.user_profile_picture),
                        "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Hello, ${User.NAME}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(Modifier.height(4.dp))
                    Text(User.PHONE, fontSize = 14.sp, color = TextSecondary)
                }
                IconButton(onClick = onNavigateToEdit) {
                    Icon(
                        Icons.Outlined.Edit,
                        null,
                        tint = TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            HorizontalDivider(
                color = DividerColor,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            menuItems.forEach { (icon, label, route) ->
                ProfileMenuRow(icon, label) { onNavigateTo(route) }
                HorizontalDivider(color = DividerColor, thickness = 0.5.dp)
            }

            ProfileMenuRow(
                Icons.AutoMirrored.Outlined.Logout,
                "Logout"
            ) {
                viewModel.onLogoutRequested()
            }
            HorizontalDivider(color = DividerColor, thickness = 0.5.dp)
        }
    }
}

@Composable
private fun ProfileMenuRow(icon: ImageVector, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(icon, contentDescription = label, tint = IconTint, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(16.dp))
        Text(label, fontSize = 15.sp, color = TextPrimary, modifier = Modifier.weight(1f))
        Icon(Icons.Default.ChevronRight, null, tint = IconTint, modifier = Modifier.size(20.dp))
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        onNavigateBack = {},
        onNavigateTo = {},
        onNavigateToEdit = {},
        onLogout = {}
    )
}
