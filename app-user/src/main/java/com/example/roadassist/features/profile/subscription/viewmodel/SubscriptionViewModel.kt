package com.example.roadassist.features.profile.subscription.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.subscriptionPlans
import com.example.roadassist.features.profile.subscription.model.SubscriptionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SubscriptionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        SubscriptionUiState(currentPlan = subscriptionPlans.firstOrNull { it.isCurrent })
    )
    val uiState: StateFlow<SubscriptionUiState> = _uiState.asStateFlow()
}
