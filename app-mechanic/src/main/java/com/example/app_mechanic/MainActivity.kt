package com.example.app_mechanic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.app_mechanic.ui.theme.RoadAssistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoadAssistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MechanicHomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
