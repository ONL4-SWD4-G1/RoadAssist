package com.example.roadassist.features.splash.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roadassist.features.splash.model.SplashUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        startSplashTimer()
    }

    private fun startSplashTimer() {
        viewModelScope.launch {
            delay(2_800)
            _uiState.update { it.copy(navigateToOnboarding = true) }
        }
    }

    fun onNavigationHandled() {
        _uiState.update { it.copy(navigateToOnboarding = false) }
    }
}