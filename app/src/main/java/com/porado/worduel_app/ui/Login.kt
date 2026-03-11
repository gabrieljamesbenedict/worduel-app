package com.porado.worduel_app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel // Make sure you import this!

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    // Inject the ViewModel here
    viewModel: LoginViewModel = viewModel()
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Call the ViewModel function when the button is clicked!
                viewModel.performLogin(
                    user = username,
                    pass = password,
                    onSuccess = { onNavigateToHome() } // Pass the navigation action
                )
            }
        ) {
            Text("Login")
        }

        // Display the loading or error status from the ViewModel
        if (viewModel.loginStatus.isNotEmpty()) {
            Text(text = viewModel.loginStatus, modifier = Modifier.padding(top = 8.dp))
        }
    }
}