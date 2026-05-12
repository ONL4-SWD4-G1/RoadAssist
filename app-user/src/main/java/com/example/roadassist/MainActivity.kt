package com.example.roadassist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.roadassist.navigation.RoadAssistNavGraph
import com.example.roadassist.theme.RoadAssistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoadAssistTheme {
                RoadAssistNavGraph()
            }
        }
    }
}
