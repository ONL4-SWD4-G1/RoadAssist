package com.example.roadassist.features.booking.feedback.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.roadassist.theme.OrangeButton

@Composable
fun RatingStars(
    rating: Int,
    onRatingChanged: (Int) -> Unit,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        (1..5).forEach { i ->
            Icon(
                imageVector = if (i <= rating) Icons.Default.Star else Icons.Outlined.StarOutline,
                contentDescription = "Star $i",
                tint = OrangeButton,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onRatingChanged(i) },
            )
        }
    }
}