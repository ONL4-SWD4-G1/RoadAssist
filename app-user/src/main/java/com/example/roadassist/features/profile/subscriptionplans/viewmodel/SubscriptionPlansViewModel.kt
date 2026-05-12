package com.example.roadassist.features.profile.subscriptionplans.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.subscriptionPlans
import com.example.roadassist.features.profile.subscriptionplans.model.SubscriptionPlansUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SubscriptionPlansViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SubscriptionPlansUiState(plans = subscriptionPlans))
    val uiState: StateFlow<SubscriptionPlansUiState> = _uiState.asStateFlow()
}
