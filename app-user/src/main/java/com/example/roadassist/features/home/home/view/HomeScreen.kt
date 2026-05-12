package com.example.roadassist.features.home.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.BatteryChargingFull
import androidx.compose.material.icons.outlined.DiscFull
import androidx.compose.material.icons.outlined.Engineering
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.LocalGasStation
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.TireRepair
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.R
import com.example.roadassist.components.HomeBottomNav
import com.example.roadassist.components.SearchBar
import com.example.roadassist.fakedata.User
import com.example.roadassist.features.home.home.viewmodel.HomeViewModel
import com.example.roadassist.navigation.Routes
import com.example.roadassist.theme.NavyBlue
import com.example.roadassist.theme.OrangeButton
import com.example.roadassist.theme.RoadAssistTheme

sealed class ServiceIcon {
    data class Vector(val icon: ImageVector) : ServiceIcon()
    data class Drawable(val resId: Int) : ServiceIcon()
}

fun serviceIcon(id: String): ServiceIcon = when (id) {
    "towing" -> ServiceIcon.Drawable(R.drawable.outline_auto_towing)
    "flat_tyre" -> ServiceIcon.Vector(Icons.Outlined.TireRepair)
    "fuel" -> ServiceIcon.Vector(Icons.Outlined.LocalGasStation)
    "battery" -> ServiceIcon.Vector(Icons.Outlined.BatteryChargingFull)
    "brake" -> ServiceIcon.Vector(Icons.Outlined.DiscFull)
    "engine" -> ServiceIcon.Vector(Icons.Outlined.Engineering)
    "key" -> ServiceIcon.Vector(Icons.Outlined.Key)
    else -> ServiceIcon.Vector(Icons.Outlined.MoreHoriz)
}


@Composable
fun HomeScreen(
    onNavigateToNotifications: () -> Unit,
    onNavigateToService: (String) -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToTrack: () -> Unit,
    onNavigateToHelp: () -> Unit,
    onNavigateToProfile: () -> Unit,
    viewModel: HomeViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = Color(0xFFF5F5F5),
        topBar = {
            HomeTopBar(
                userName = User.NAME.substringBefore(" "),
                onNotifications = onNavigateToNotifications,
                onProfile = onNavigateToProfile,
            )
        },
        bottomBar = {
            HomeBottomNav(
                selected = Routes.HOME,
                onNavigate = {
                    when (it) {
                        Routes.SERVICES -> onNavigateToServices()
                        Routes.TRACK -> onNavigateToTrack()
                        Routes.CONTACT -> onNavigateToHelp()
                    }
                },
            )
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 16.dp),
        ) {
            item {
                SearchBar(
                    value = uiState.searchQuery,
                    onValueChange = viewModel::onSearchQueryChange,
                )

            }
            item { PromoBanner(modifier = Modifier.padding(horizontal = 16.dp)) }
            item {
                Text(
                    "Services", fontWeight = FontWeight.Bold, fontSize = 18.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 20.dp, bottom = 12.dp),
                )
            }
            item {
                ServicesGrid(
                    services = uiState.services,
                    onServiceSelected = onNavigateToService,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
            item {
                Text(
                    "Nearby Garages", fontWeight = FontWeight.Bold, fontSize = 18.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 20.dp, bottom = 12.dp),
                )
            }
            items(uiState.garages) { garage ->
                GarageCard(
                    garage = garage,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                )
            }
        }
    }
}

@Composable
private fun HomeTopBar(userName: String, onNotifications: () -> Unit, onProfile: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(NavyBlue)
            .clip(RoundedCornerShape(bottomStart = 18.dp, bottomEnd = 18.dp))
            .padding(horizontal = 16.dp, vertical = 14.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF3A3F9E))
                    .clickable { onProfile() },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Outlined.Person,
                    null,
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            Text(
                "Hello $userName..", color = Color.White, fontWeight = FontWeight.Bold,
                fontSize = 18.sp, modifier = Modifier.weight(1f),
            )
            Box {
                IconButton(onClick = onNotifications) {
                    Icon(Icons.Default.Notifications, null, tint = Color.White)
                }
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(OrangeButton, CircleShape)
                        .align(Alignment.TopEnd),
                )
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun HomeScreenPreview() {
    RoadAssistTheme {
        HomeScreen(
            onNavigateToNotifications = {},
            onNavigateToService = {},
            onNavigateToServices = {},
            onNavigateToTrack = {},
            onNavigateToHelp = {},
            onNavigateToProfile = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun BottomNavPreview() {
    RoadAssistTheme { HomeBottomNav(selected = Routes.HOME, onNavigate = {}) }
}
