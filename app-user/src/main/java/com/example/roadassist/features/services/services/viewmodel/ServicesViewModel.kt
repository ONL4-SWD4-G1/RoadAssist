package com.example.roadassist.features.services.services.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.TireRepair
import androidx.compose.material.icons.filled.VpnKey
import androidx.lifecycle.ViewModel
import com.example.roadassist.features.services.services.model.ServiceItem

class ServicesViewModel : ViewModel() {

    val services: List<ServiceItem> = listOf(
        ServiceItem(Icons.Default.DirectionsCar, "Towing"),
        ServiceItem(Icons.Default.TireRepair, "Flat tyre"),
        ServiceItem(Icons.Default.LocalGasStation, "Fuel"),
        ServiceItem(Icons.Default.BatteryChargingFull, "Battery"),
        ServiceItem(Icons.Default.Construction, "Brake"),
        ServiceItem(Icons.Default.Settings, "Engine"),
        ServiceItem(Icons.Default.VpnKey, "Key retrieval"),
        ServiceItem(Icons.Default.MoreHoriz, "Others")
    )

    fun getServiceRows(): List<List<ServiceItem>> = services.chunked(2)
}