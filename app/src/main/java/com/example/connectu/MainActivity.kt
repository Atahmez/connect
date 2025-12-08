package com.example.connectu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.navigation.compose.rememberNavController
import com.example.connectu.nav.NavGraph
import com.example.connectu.ui.theme.ConnectUTheme
import androidx.compose.material3.Surface as Surface1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConnectUTheme {
                val navController = rememberNavController()

                Surface1(color = colorScheme.background) {
                    NavGraph(navController = navController)
                }
            }
        }
    }
}
