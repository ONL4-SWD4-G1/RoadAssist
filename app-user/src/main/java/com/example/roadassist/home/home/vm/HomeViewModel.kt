package com.example.roadassist.home.home.vm

import com.example.roadassist.home.home.model.GarageItem
import com.example.roadassist.home.home.model.HomeUiState
import com.example.roadassist.home.home.model.ServiceItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        _uiState.update {
            it.copy(
                services = listOf(
                    ServiceItem(Icons.Default.DirectionsCar, "Towing"),
                    ServiceItem(Icons.Default.TireRepair, "Flat tyre"),
                    ServiceItem(Icons.Default.LocalGasStation, "Fuel"),
                    ServiceItem(Icons.Default.BatteryChargingFull, "Battery"),
                    ServiceItem(Icons.Default.Construction, "Brake"),
                    ServiceItem(Icons.Default.Settings, "Engine"),
                    ServiceItem(Icons.Default.VpnKey, "Key retrieval"),
                    ServiceItem(Icons.Default.MoreHoriz, "Others")
                ),
                garages = listOf(
                    GarageItem("SP Mechanics", "Patna, Cbse", "6km", 4.5f),
                    GarageItem("SA John Garage", "Ola, Cbse", "2km", 3.5f),
                    GarageItem("MRT Garage", "Indian Polis", "8km", 4.0f)
                )
            )
        }
    }

    // ---- Actions ----

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onProfileClick() {
        _uiState.update { it.copy(navigateToProfile = true) }
    }

    fun onNotificationClick() {
        _uiState.update { it.copy(navigateToNotifications = true) }
    }

    fun onNavigationHandled() {
        _uiState.update {
            it.copy(
                navigateToProfile = false,
                navigateToNotifications = false
            )
        }
    }
}