package com.porado.worduel_app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.porado.worduel_app.data.repository.UserRepository
import com.porado.worduel_app.ui.component.UserList

@Composable
fun Root(modifier: Modifier = Modifier) {
    UserList()
}