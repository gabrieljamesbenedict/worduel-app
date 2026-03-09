package com.porado.worduel_app.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.porado.worduel_app.R
import androidx.compose.ui.tooling.preview.Preview

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
                .background(BackgroundColor)
                .padding(horizontal = 32.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.worduel_logo),
                contentDescription = "Worduel Logo",
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(64.dp))

            GameButton(text = "Solo", onClick = onPlaySoloClick)
            Spacer(modifier = Modifier.height(16.dp))


            GameButton(text = "Duel", onClick = onPlayDuelClick)
            Spacer(modifier = Modifier.height(16.dp))

            LoginButton(text = "Login", onClick = onLoginClick)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Reusable Composable for your main game modes
@Composable
fun GameButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(230.dp)
            .height(55.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = GreenColor
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
@Composable
fun LoginButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(230.dp)
            .height(55.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = BackgroundColor
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        border = BorderStroke(3.dp, GreenColor)
    ) {
        Text(
            text = text.uppercase(), // Uppercase looks great for game menus
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onPlaySoloClick = {},
        onPlayDuelClick = {},
        onPlayChallengeClick = {},
        onLoginClick = {}
    )
}
