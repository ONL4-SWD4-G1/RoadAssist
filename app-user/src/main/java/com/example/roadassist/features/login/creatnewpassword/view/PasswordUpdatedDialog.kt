package com.example.roadassist.features.login.creatnewpassword.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.roadassist.components.SuccessDialog
import com.example.roadassist.theme.RoadAssistTheme

@Composable
fun PasswordUpdatedDialog(onExploreClick: () -> Unit) {
    SuccessDialog(
        message = "Password updated successfully",
        buttonText = "Explore",
        onButtonClick = onExploreClick
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PasswordUpdatedDialogPreview() {
    RoadAssistTheme {
        PasswordUpdatedDialog(onExploreClick = {})
    }
}