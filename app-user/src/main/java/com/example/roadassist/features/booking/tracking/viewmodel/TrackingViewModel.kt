package com.example.roadassist.features.booking.tracking.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.activeBooking
import com.example.roadassist.features.booking.tracking.model.TrackingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TrackingViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TrackingUiState(booking = activeBooking))
    val uiState: StateFlow<TrackingUiState> = _uiState.asStateFlow()
}