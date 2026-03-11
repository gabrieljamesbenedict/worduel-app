package com.porado.worduel_app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel // Added import

@Composable
fun SignupScreen(
    onNavigateToLogin: () -> Unit,
    onSignupSuccess: () -> Unit, // Replaced onSignupClick with a success callback
    viewModel: SignupViewModel = viewModel() // Injected the ViewModel
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val worduelYellow = Color(0xFFB59F3B)
    val GreenColor = Color(0xFF538D4E) // Assuming this is your Wordle green

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "JOIN WORDUEL",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = GreenColor
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                // Delegate the logic to the ViewModel
                viewModel.performSignup(
                    user = username,
                    pass = password,
                    onSuccess = onSignupSuccess
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GreenColor)
        ) {
            Text("Create Account", fontSize = 18.sp)
        }

        // Display the status message from the ViewModel
        if (viewModel.signupStatus.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = viewModel.signupStatus,
                color = if (viewModel.signupStatus.contains("failed")) Color.Red else Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onNavigateToLogin) {
            Text("Already have an account? Log in", color = Color.Gray)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignupScreenPreview() {
    SignupScreen(
        onNavigateToLogin = {},
        onSignupSuccess = {}
    )
}