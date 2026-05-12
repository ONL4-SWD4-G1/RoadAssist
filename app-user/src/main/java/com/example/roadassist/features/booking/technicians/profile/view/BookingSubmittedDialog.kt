package com.example.roadassist.features.booking.technicians.profile.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.roadassist.components.SuccessDialog
import com.example.roadassist.theme.RoadAssistTheme

@Composable
fun BookingSubmittedDialog(onTrack: () -> Unit) {

    SuccessDialog(
        "Your booking has been submitted.",
        "Service provider is arriving shortly",
        "Track",
        onTrack
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun BookingSubmittedDialogPreview() {
    RoadAssistTheme {
        BookingSubmittedDialog(onTrack = {})
    }
}