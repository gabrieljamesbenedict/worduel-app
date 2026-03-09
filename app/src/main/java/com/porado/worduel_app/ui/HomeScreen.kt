package com.porado.worduel_app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onPlaySoloClick: () -> Unit,
    onPlayDuelClick: () -> Unit,
    onPlayChallengeClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    // A Surface provides a good background for a game app
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // 1. Logo Space
            // TODO: Replace with your actual logo drawable (e.g., R.drawable.worduel_logo)
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(100.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("LOGO", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.height(64.dp))

            // 2. Play Solo
            GameButton(text = "Play Solo", onClick = onPlaySoloClick)

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Play Duel
            GameButton(text = "Play Duel", onClick = onPlayDuelClick)

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Play Challenge
            GameButton(text = "Play Challenge", onClick = onPlayChallengeClick)

            Spacer(modifier = Modifier.height(48.dp))

            // 5. Login Button (Styled differently as a secondary action)
            TextButton(
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Log In to Save Progress",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

// Reusable Composable for your main game modes
@Composable
fun GameButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp), // Slightly taller for that chunky, tap-friendly game feel
        shape = RoundedCornerShape(24.dp), // Very rounded edges mimicking your wireframe
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = text.uppercase(), // Uppercase looks great for game menus
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp
        )
    }
}
