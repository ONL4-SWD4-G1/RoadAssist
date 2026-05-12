package com.example.roadassist.features.services.services.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.services
import com.example.roadassist.features.services.services.model.ServicesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ServicesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ServicesUiState(services = services))
    val uiState: StateFlow<ServicesUiState> = _uiState.asStateFlow()
}
