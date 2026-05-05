package com.example.roadassist.services.services.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.lifecycle.ViewModel
import com.example.roadassist.services.services.model.ServiceItem

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