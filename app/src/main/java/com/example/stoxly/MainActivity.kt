package com.example.stoxly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.stoxly.navigation.NavGraph
import com.example.stoxly.ui.theme.StoxlyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StoxlyTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavGraph()
                   //AuthApp()
                }
            }
        }
    }
}

