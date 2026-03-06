package com.porado.worduel_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Make sure both screens are imported!
import com.porado.worduel_app.auth.LoginScreen
import com.porado.worduel_app.auth.SignupScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Call our new navigation controller function
            WorduelNavigation()
        }
    }
}

@Composable
fun WorduelNavigation() {
    // This controller manages the back stack and screen swapping
    val navController = rememberNavController()

    // The NavHost links the controller to specific screen routes
    NavHost(navController = navController, startDestination = "login") {

        // Route 1: The Login Screen
        composable("login") {
            LoginScreen(
                onNavigateToSignup = {
                    // When the signup text is clicked, navigate to the signup route
                    navController.navigate("signup")
                },
                onLoginClick = { email, password ->
                    /* TODO: API call to authenticate user */
                }
            )
        }

        // Route 2: The Signup Screen
        composable("signup") {
            SignupScreen(
                onNavigateToLogin = {
                    // Go back to the login screen
                    navController.popBackStack()
                },
                onSignupClick = { username, email, password ->
                    /* TODO: API call to register user */
                }
            )
        }
    }
}