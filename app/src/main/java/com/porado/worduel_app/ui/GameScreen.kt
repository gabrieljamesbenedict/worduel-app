package com.porado.worduel_app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val BackgroundColor = Color(0xFF121213)
val TileColor = Color(0xFF121213)
val TileBorderColor = Color(0xFF3A3A3C)
val KeyColor = Color(0xFF818384)

val GreenColor = Color(0xFF538D4E)

val OrangeColor = Color(0xFFFFD45A)

val TextColor = Color.White

val keyRows = listOf(
    listOf("Q","W","E","R","T","Y","U","I","O","P"),
    listOf("A","S","D","F","G","H","J","K","L"),
    listOf("ENTER","Z","X","C","V","B","N","M","⌫")
)

@Composable
fun WordleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            HomeButton(text = "Home", onClick = { /* navigate */ })
        }

        GameHeader(
            playerInitial = "A",
            opponentUsername = "Player 2",
            playerScore = "0",
            opponentScore = "0"
        )

        Spacer(modifier = Modifier.height(4.dp))

        Divider()

        Spacer(modifier = Modifier.height(24.dp))
        WordleGrid()

        Spacer(modifier = Modifier.height(32.dp))
        
        WordleKeyboard()
    }
}
@Composable
fun HomeButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .background(TileBorderColor, RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = text,
            tint = TextColor,
            modifier = Modifier.size(24.dp)
        )
    }
}
@Composable
fun GameHeader(
    playerInitial: String,
    opponentUsername: String,
    playerScore: String,
    opponentScore: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1A1A1B), RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Text(
                    text = "playing against:",
                    color = Color(0xFFAAAAAA),
                    fontSize = 13.sp
                )
                Text(
                    text = opponentUsername,
                    color = TextColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Score row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = playerScore,
                color = GreenColor,
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "vs",
                color = Color(0xFFAAAAAA),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = opponentScore,
                color = OrangeColor,
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp
            )
        }
    }
}

@Composable
fun Divider() {
    Box(
        modifier = Modifier
            .width(320.dp)
            .height(1.dp)
            .background(TileBorderColor)
    )
}

@Composable
fun WordleGrid() {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        repeat(6) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .size(58.dp)
                            .background(TileColor)
                            .border(2.dp, TileBorderColor)
                    )
                }
            }
        }
    }
}

@Composable
fun WordleKeyboard() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        keyRows.forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                row.forEach { key ->
                    WordleKey(key)
                }
            }
        }
    }
}

@Composable
fun WordleKey(label: String) {
    val isWide = label.length > 1
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(56.dp)
            .width(if (isWide) 40.dp else 30.dp)
            .background(KeyColor, shape = RoundedCornerShape(4.dp))
    ) {
        Text(
            text = label,
            color = TextColor,
            fontSize = if (isWide) 11.sp else 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WordleScreenPreview() {
    WordleScreen()
}