package com.example.connectu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.connectu.ui.components.FriendCard

@Composable
fun ConnectScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Connect",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Suggested friends based on shared interests and campus life.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔵 ROUNDED FRIEND CARDS (TODO COMPLETE)
        FriendCard(
            name = "Alex Johnson",
            interests = "Computer Science • Gym • Gaming"
        )

        FriendCard(
            name = "Maria Lopez",
            interests = "Biology • Volunteering • Hiking"
        )

        FriendCard(
            name = "Chris Patel",
            interests = "Business • Startups • Networking"
        )
    }
}
