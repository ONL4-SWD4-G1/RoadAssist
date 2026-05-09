package com.example.roadassist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.roadassist.navigation.RoadAssistNavGraph
import com.example.roadassist.theme.RoadAssistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoadAssistTheme {
                MaterialTheme {
                    Surface (modifier = Modifier.fillMaxSize()) {
                        RoadAssistNavGraph()
                    }
                }
            }
        }
    }
}
