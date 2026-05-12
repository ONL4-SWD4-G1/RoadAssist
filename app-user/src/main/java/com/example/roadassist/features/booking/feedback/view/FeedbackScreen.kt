package com.example.roadassist.features.booking.feedback.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.components.PrimaryButton
import com.example.roadassist.components.RoundedTextField
import com.example.roadassist.components.SectionHeader
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.features.booking.feedback.viewmodel.FeedbackViewModel
import com.example.roadassist.theme.RoadAssistTheme
import com.example.roadassist.theme.TextSecondary

@Composable
fun FeedbackScreen(
    onSubmit: () -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: FeedbackViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UserTopBar("Feedback", onNavigateBack) },
        containerColor = Color.White,
        bottomBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                PrimaryButton("Submit", onSubmit)
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(8.dp))
            SectionHeader(text = "Rate us", fontSize = 20)
            Spacer(Modifier.height(20.dp))
            SectionHeader(
                text = "How did we do?",
                fontSize = 14,
                fontWeight = FontWeight.Normal,
                color = TextSecondary,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.height(12.dp))

            // RatingStars now driven by ViewModel state
            RatingStars(
                rating = uiState.rating,
                onRatingChanged = { viewModel.onRatingChanged(it) },
            )

            Spacer(Modifier.height(24.dp))
            SectionHeader(
                text = "Share more about it",
                fontSize = 14,
                fontWeight = FontWeight.Normal,
                color = TextSecondary,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.height(10.dp))

            RoundedTextField(
                value = uiState.comment,
                onValueChange = { viewModel.onCommentChanged(it) },
                placeholder = "Tell us about your experience...",
                modifier = Modifier.height(160.dp),
                maxLines = 6,
            )
            Spacer(Modifier.height(80.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FeedbackEmptyPreview() {
    RoadAssistTheme { FeedbackScreen(onSubmit = {}, onNavigateBack = {}) }
}