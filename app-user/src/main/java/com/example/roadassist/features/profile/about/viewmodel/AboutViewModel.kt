package com.example.roadassist.features.profile.about.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.features.profile.about.model.AboutUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AboutViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AboutUiState())
    val uiState: StateFlow<AboutUiState> = _uiState.asStateFlow()
}
