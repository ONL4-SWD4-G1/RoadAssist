package com.example.roadassist.features.booking.feedback.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.features.booking.feedback.model.FeedbackUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FeedbackViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FeedbackUiState())
    val uiState: StateFlow<FeedbackUiState> = _uiState.asStateFlow()

    fun onRatingChanged(rating: Int) {
        _uiState.update { it.copy(rating = rating) }
    }

    fun onCommentChanged(comment: String) {
        _uiState.update { it.copy(comment = comment) }
    }
}
