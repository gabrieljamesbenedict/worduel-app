package com.porado.worduel_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.porado.worduel_app.ui.Root
import com.porado.worduel_app.ui.theme.WorduelappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorduelappTheme {
                Root(modifier = Modifier.padding(all = 24.dp))
            }
        }
    }
}