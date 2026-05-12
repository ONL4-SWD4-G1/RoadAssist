package com.example.roadassist.features.profile.history.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.serviceHistory
import com.example.roadassist.features.profile.history.model.ServiceHistoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ServiceHistoryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ServiceHistoryUiState(items = serviceHistory))
    val uiState: StateFlow<ServiceHistoryUiState> = _uiState.asStateFlow()
}
