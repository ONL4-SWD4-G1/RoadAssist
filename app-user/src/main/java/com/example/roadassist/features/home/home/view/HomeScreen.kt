package com.example.roadassist.features.home.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Garage
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.roadassist.features.home.home.model.GarageItem
import com.example.roadassist.features.home.home.model.HomeUiState
import com.example.roadassist.features.home.home.model.ServiceItem
import com.example.roadassist.features.home.home.vm.HomeViewModel
import com.example.roadassist.navigation.Routes
import com.example.roadassist.theme.Background
import com.example.roadassist.theme.GoldStar
import com.example.roadassist.theme.LightBlue
import com.example.roadassist.theme.NavyBlue
import com.example.roadassist.theme.OrangeAccent
import com.example.roadassist.theme.TextGray
import com.example.roadassist.theme.TextPrimary
import com.example.roadassist.theme.White

@Composable
fun HomeScreen(
    navController: NavController,
    onNotificationClick: () -> Unit = {},
    onClickAction: () -> Unit = {},
    onServicesClick: () -> Unit = {},
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Navigation Handler
    LaunchedEffect(uiState.navigateToProfile, uiState.navigateToNotifications) {
        when {
            uiState.navigateToProfile -> {
                viewModel.onNavigationHandled()
                onClickAction()
            }

            uiState.navigateToNotifications -> {
                viewModel.onNavigationHandled()
                onNotificationClick()
            }
        }
    }

    HomeContent(
        uiState = uiState,
        navController = navController,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onProfileClick = viewModel::onProfileClick,
        onNotificationClick = viewModel::onNotificationClick,
        onServicesClick = onServicesClick
    )
}

@Composable
private fun HomeContent(
    uiState: HomeUiState,
    navController: NavController,
    onSearchQueryChange: (String) -> Unit,
    onProfileClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onServicesClick: () -> Unit
) {
    Scaffold(
        bottomBar = { BottomNavBar(navController = navController, selectedIndex = 0) },
        containerColor = Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            HomeTopBar(
                onNotificationClick = onNotificationClick,
                onProfileClick = onProfileClick
            )

            HomeSearchBar(
                query = uiState.searchQuery,
                onQueryChange = onSearchQueryChange
            )

            PromoBanner()

            SectionTitle("Services")

            // ✅ بدل ما نبعت navController بنبعت lambda
            ServicesGrid(
                services = uiState.services,
                onServiceClick = { label ->
                    when (label) {
                        "Others" -> onServicesClick()
                        // أضف cases تانية هنا لو محتاج
                    }
                }
            )

            SectionTitle("Nearby Garages")
            uiState.garages.forEach { GarageCard(garage = it) }

            MembershipCard()

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController, selectedIndex: Int = 0) {
    val items = listOf(
        Triple(Icons.Default.Home, "Home", Routes.HOME),
        Triple(Icons.Default.Build, "Services", Routes.SERVICES),
        Triple(Icons.Default.MyLocation, "Track", Routes.HOME),
        Triple(Icons.Default.HelpOutline, "Help", Routes.HOME)
    )

    NavigationBar(containerColor = White, tonalElevation = 8.dp) {
        items.forEachIndexed { index, (icon, label, route) ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = {
                    if (index != selectedIndex) {
                        navController.navigate(route) {
                            launchSingleTop = true
                            popUpTo(Routes.HOME) { saveState = true }
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(imageVector = icon, contentDescription = label) },
                label = { Text(label, fontSize = 11.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NavyBlue,
                    selectedTextColor = NavyBlue,
                    unselectedIconColor = TextGray,
                    indicatorColor = LightBlue
                )
            )
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = TextPrimary,
        modifier = Modifier.padding(start = 16.dp, top = 20.dp, bottom = 10.dp)
    )
}

@Composable
private fun HomeTopBar(
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(NavyBlue)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(OrangeAccent),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = onProfileClick) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "User",
                        tint = White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                "Hello User...",
                color = White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        IconButton(onClick = onNotificationClick) {
            Icon(
                Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = White
            )
        }
    }
}

@Composable
private fun HomeSearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Search", color = TextGray) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextGray) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            focusedBorderColor = NavyBlue,
            unfocusedBorderColor = Color.Transparent
        ),
        singleLine = true
    )
}

@Composable
private fun PromoBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(160.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFF2D2E8F), Color(0xFF5B5EA6))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(20.dp)
        ) {
            Text(
                "Avail 24/7 roadside",
                color = White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "assistance",
                color = White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("Anywhere, Anytime", color = Color(0xFFBBBEFF), fontSize = 12.sp)
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0x22FFFFFF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.SupportAgent,
                contentDescription = "Agent",
                tint = White,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Composable
private fun ServicesGrid(
    services: List<ServiceItem>,
    onServiceClick: (String) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        services.chunked(4).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowItems.forEach { service ->
                    ServiceIconCard(
                        service = service,
                        onServiceClick = onServiceClick   // ✅
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// ✅ اتغير: بدل navController بياخد lambda
@Composable
private fun ServiceIconCard(
    service: ServiceItem,
    onServiceClick: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(White)
            .clickable { onServiceClick(service.label) }
            .padding(vertical = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(LightBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = service.icon,
                contentDescription = service.label,
                tint = OrangeAccent,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            service.label,
            fontSize = 11.sp,
            color = TextPrimary,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Composable
private fun GarageCard(garage: GarageItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(White)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFDDE4FF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Garage,
                contentDescription = "Garage",
                tint = NavyBlue,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(garage.name, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextPrimary)
            Text(garage.location, fontSize = 12.sp, color = TextGray)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    tint = GoldStar,
                    modifier = Modifier.size(14.dp)
                )
                Text(" ${garage.rating}", fontSize = 12.sp, color = TextGray)
            }
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(garage.distance, fontSize = 12.sp, color = TextGray)
            Spacer(modifier = Modifier.height(6.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = OrangeAccent),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text(
                    "Book",
                    fontSize = 12.sp,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun MembershipCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFFFFF3CD), Color(0xFFFFE082))
                )
            )
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    "Premium Membership",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF5D4037)
                )
                Text("5% Discount on All Services", fontSize = 12.sp, color = Color(0xFF795548))
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5D4037)),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 6.dp)
                ) {
                    Text("Join", color = White, fontSize = 12.sp)
                }
            }

            Icon(
                Icons.Default.Diamond,
                contentDescription = "Premium",
                tint = Color(0xFF5D4037),
                modifier = Modifier.size(48.dp)
            )
        }
    }
}