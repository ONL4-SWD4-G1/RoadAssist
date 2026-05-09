package com.example.roadassist.features.onboarding.model

import com.example.roadassist.R

data class OnboardingUiState(
    val image: Int,
    val title: String,
    val subtitle: String,
    val buttonText: String
)

val onboardingPages = listOf(
    OnboardingUiState(R.drawable.onboarding1, "Feeling Stuck?", "No Problem. We'll Fix It.\nJust Request service.", "Continue"),
    OnboardingUiState(R.drawable.onboarding2, "Track assistant", "Real-time tracking of your service provider", "Continue"),
    OnboardingUiState(R.drawable.onboarding3, "Quality Service", "Rest assured, your vehicle is in expert's hand", "Get Started")
)