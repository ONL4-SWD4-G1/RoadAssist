package com.example.roadassist.features.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.features.onboarding.model.OnboardingUiState
import com.example.roadassist.features.onboarding.model.onboardingPages

class OnboardingViewModel : ViewModel() {

    val pages: List<OnboardingUiState> = onboardingPages

    val totalPages: Int = pages.size

    fun isLastPage(index: Int) = index == pages.lastIndex

    fun onNextPage(currentIndex: Int): Int? {
        return if (!isLastPage(currentIndex)) currentIndex + 1 else null
    }

    fun getCurrentButtonText(index: Int) = pages[index].buttonText
}