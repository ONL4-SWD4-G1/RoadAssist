package com.example.roadassist.features.booking.payment.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.roadassist.components.SuccessDialog
import com.example.roadassist.theme.RoadAssistTheme


@Composable
fun PaymentSuccessDialog(onRateUs: () -> Unit) {
    SuccessDialog(
        message = "Payment Successful",
        buttonText = "Rate us",
        onButtonClick = onRateUs
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PaymentSuccessDialogPreview() {
    RoadAssistTheme {
        PaymentSuccessDialog {

        }
    }
}