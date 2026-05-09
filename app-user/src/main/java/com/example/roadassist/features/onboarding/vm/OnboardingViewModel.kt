package com.example.roadassist.features.onboarding.vm

import androidx.lifecycle.ViewModel
import com.example.roadassist.features.onboarding.model.OnboardingUiState
import com.example.roadassist.features.onboarding.model.onboardingPages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingViewModel : ViewModel() {

    val pages: List<OnboardingUiState> = onboardingPages

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    val totalPages: Int = pages.size

    fun isLastPage(index: Int) = index == pages.lastIndex

    fun onNextPage(currentIndex: Int): Int? {
        return if (!isLastPage(currentIndex)) currentIndex + 1 else null
    }

    fun getCurrentButtonText(index: Int) = pages[index].buttonText
}