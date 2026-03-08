package com.porado.worduel_app.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Score()
        Spacer(modifier = Modifier.height(16.dp))
        OpponentsName()
        Spacer(modifier = Modifier.height(16.dp))
        OpponentStats()
        Spacer(modifier = Modifier.height(5.dp))
        OpponentsScore()
        Spacer(modifier = Modifier.height(32.dp))
        Divider()
        Spacer(modifier = Modifier.height(24.dp))
        WordleGrid()
        Spacer(modifier = Modifier.height(32.dp))
        WordleKeyboard()
    }
}
@Composable
fun Score(){
    Text(
        text = "Score",
        color = TextColor,
        fontSize = 36.sp,
        fontWeight =  FontWeight.Bold
    )
}
@Composable
fun OpponentsName() {
    Text(
        text = "Opponent's Name",
        color = TextColor,
        fontSize = 24.sp,
    )
}
@Composable
fun OpponentStats() {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(TileColor)
                            .border(2.dp, TileBorderColor)
                    )
                }
            }
    }
}
@Composable
fun OpponentsScore() {
    Text(
        text = "OpponentsScore",
        color = TextColor,
        fontSize = 20.sp,
    )
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
                            .size(44.dp)
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
            .width(if (isWide) 44.dp else 30.dp)
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