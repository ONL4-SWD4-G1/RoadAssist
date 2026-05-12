package com.example.roadassist.features.profile.subscriptionplans.model

import com.example.roadassist.fakedata.SubscriptionPlan

data class SubscriptionPlansUiState(
    val plans: List<SubscriptionPlan> = emptyList(),
)
