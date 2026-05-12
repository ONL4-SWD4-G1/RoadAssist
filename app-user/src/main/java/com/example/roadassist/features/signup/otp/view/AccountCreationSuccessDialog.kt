package com.example.roadassist.features.signup.otp.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.roadassist.components.SuccessDialog
import com.example.roadassist.theme.RoadAssistTheme

@Composable
fun AccountCreationSuccessDialog(onExploreClick: () -> Unit) {

    SuccessDialog(
        message = "Account created successfully",
        buttonText = "Explore",
        onButtonClick = onExploreClick
    )
}


@Composable
@Preview(showSystemUi = true, showBackground = true)
fun AccountCreationSuccessDialogPreview() {

    RoadAssistTheme {
        AccountCreationSuccessDialog(
            onExploreClick = {}
        )
    }
}