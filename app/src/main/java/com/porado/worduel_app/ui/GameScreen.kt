package com.porado.worduel_app.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val BackgroundColor = Color(0xFF121213)
val TileColor     = Color(0xFF121213)
val TileBorderColor = Color(0xFF3A3A3C)
val KeyColor      = Color(0xFF818384)
val TextColor     = Color.White
val GreenColor    = Color(0xFF538D4E)
val YellowColor   = Color(0xFFB59F3B)
val GreyColor     = Color(0xFF3A3A3C)

//sample word
const val TARGET_WORD = "CRANE"

val keyRows = listOf(
    listOf("Q","W","E","R","T","Y","U","I","O","P"),
    listOf("A","S","D","F","G","H","J","K","L"),
    listOf("ENTER","Z","X","C","V","B","N","M","⌫")
)

enum class TileState { EMPTY, FILLED, CORRECT, PRESENT, ABSENT }

data class Tile(val letter: String = "", val state: TileState = TileState.EMPTY)

@Composable
fun WordleScreen() {
    val grid = remember {
        mutableStateListOf(*Array(6) { mutableStateListOf(*Array(5) { Tile() }) })
    }
    var currentRow by remember { mutableStateOf(0) }
    var currentCol by remember { mutableStateOf(0) }
    var gameOver   by remember { mutableStateOf(false) }
    var message    by remember { mutableStateOf("") }
    val shakingRow  = remember { mutableStateOf(-1) }
    val keyColors   = remember { mutableStateMapOf<String, TileState>() }

    fun onKey(key: String) {
        if (gameOver) return
        when (key) {
            "⌫" -> {
                if (currentCol > 0) {
                    currentCol--
                    grid[currentRow][currentCol] = Tile()
                }
            }
            "ENTER" -> {
                if (currentCol < 5) {
                    shakingRow.value = currentRow
                    message = "Not enough letters"
                    return
                }
                val guess  = grid[currentRow].joinToString("") { it.letter }
                val target = TARGET_WORD
                val result = Array(5) { TileState.ABSENT }
                val targetCounts = target.groupingBy { it }.eachCount().toMutableMap()

                // Pass 1 — correct position
                for (i in 0..4) {
                    if (guess[i] == target[i]) {
                        result[i] = TileState.CORRECT
                        targetCounts[guess[i]] = targetCounts[guess[i]]!! - 1
                    }
                }
                // Pass 2 — wrong position
                for (i in 0..4) {
                    if (result[i] != TileState.CORRECT) {
                        val c = guess[i]
                        if ((targetCounts[c] ?: 0) > 0) {
                            result[i] = TileState.PRESENT
                            targetCounts[c] = targetCounts[c]!! - 1
                        }
                    }
                }

                for (i in 0..4) {
                    val letter = grid[currentRow][i].letter
                    grid[currentRow][i] = grid[currentRow][i].copy(state = result[i])
                    val existing = keyColors[letter]
                    if (existing != TileState.CORRECT) {
                        if (existing != TileState.PRESENT || result[i] == TileState.CORRECT) {
                            keyColors[letter] = result[i]
                        }
                    }
                }

                when {
                    guess == target -> { message = "Win!"; gameOver = true }
                    currentRow == 5 -> { message = "The word was $target"; gameOver = true }
                    else -> { currentRow++; currentCol = 0; message = "" }
                }
            }
            else -> {
                if (currentCol < 5) {
                    grid[currentRow][currentCol] = Tile(key, TileState.FILLED)
                    currentCol++
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Score()
        Spacer(Modifier.height(16.dp))
        OpponentsName()
        Spacer(Modifier.height(16.dp))
        OpponentStats()
        Spacer(Modifier.height(5.dp))
        OpponentsScore()
        Spacer(Modifier.height(32.dp))
        Divider()
        Spacer(Modifier.height(24.dp))

        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = TextColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color(0xFF3A3A3C), RoundedCornerShape(4.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            )
            Spacer(Modifier.height(12.dp))
        }

        WordleGrid(grid = grid, shakingRow = shakingRow)
        Spacer(Modifier.height(32.dp))
        WordleKeyboard(keyColors = keyColors, onKey = ::onKey)
    }
}

// Opponent Stats

@Composable fun Score() {
    Text("Score", color = TextColor, fontSize = 36.sp, fontWeight = FontWeight.Bold)
}

@Composable fun OpponentsName() {
    Text("Opponent's Name", color = TextColor, fontSize = 24.sp)
}

@Composable fun OpponentStats() {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        repeat(5) {
            Box(Modifier.size(30.dp).background(TileColor).border(2.dp, TileBorderColor))
        }
    }
}

@Composable fun OpponentsScore() {
    Text("OpponentsScore", color = TextColor, fontSize = 20.sp)
}

@Composable fun Divider() {
    Box(Modifier.width(320.dp).height(1.dp).background(TileBorderColor))
}

//wordle grid

@Composable
fun WordleGrid(grid: List<List<Tile>>, shakingRow: MutableState<Int>) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        grid.forEachIndexed { rowIdx, row ->
            ShakingRow(
                shaking   = shakingRow.value == rowIdx,
                onShakeDone = { shakingRow.value = -1 }
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    row.forEach { tile -> WordleTile(tile) }
                }
            }
        }
    }
}

@Composable
fun ShakingRow(shaking: Boolean, onShakeDone: () -> Unit, content: @Composable () -> Unit) {
    val offsetX = remember { Animatable(0f) }
    LaunchedEffect(shaking) {
        if (shaking) {
            repeat(5) { i ->
                offsetX.animateTo(if (i % 2 == 0) 10f else -10f, tween(50))
            }
            offsetX.animateTo(0f, tween(50))
            onShakeDone()
        }
    }
    Box(Modifier.offset(x = offsetX.value.dp)) { content() }
}

@Composable
fun WordleTile(tile: Tile) {
    val bg = when (tile.state) {
        TileState.CORRECT -> GreenColor
        TileState.PRESENT -> YellowColor
        TileState.ABSENT  -> GreyColor
        else              -> TileColor
    }
    val border = when (tile.state) {
        TileState.FILLED -> Color(0xFF565758)
        TileState.EMPTY  -> TileBorderColor
        else             -> Color.Transparent
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(44.dp).background(bg).border(2.dp, border)
    ) {
        if (tile.letter.isNotEmpty()) {
            Text(tile.letter, color = TextColor, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// keyboard

@Composable
fun WordleKeyboard(keyColors: Map<String, TileState>, onKey: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        keyRows.forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                row.forEach { key -> WordleKey(key, keyColors[key], onKey) }
            }
        }
    }
}

@Composable
fun WordleKey(label: String, state: TileState?, onKey: (String) -> Unit) {
    val isWide = label.length > 1
    val bg = when (state) {
        TileState.CORRECT -> GreenColor
        TileState.PRESENT -> YellowColor
        TileState.ABSENT  -> GreyColor
        else              -> KeyColor
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(56.dp)
            .width(if (isWide) 44.dp else 30.dp)
            .background(bg, RoundedCornerShape(4.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onKey(label) }
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