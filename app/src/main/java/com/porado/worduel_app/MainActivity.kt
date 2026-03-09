package com.porado.worduel_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.porado.worduel_app.auth.LoginScreen
import com.porado.worduel_app.auth.SignupScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WorduelNavigation()
        }
    }
}

@Composable
fun WorduelNavigation() {
    // This controller manages the back stack and screen swapping
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        // Route 1: The Home Screen (Your new screen!)
        composable("home") {
            HomeScreen(
                onPlaySoloClick = {
                    /* TODO: Navigate to Solo Game Screen */
                },
                onPlayDuelClick = {
                    /* TODO: Navigate to Duel Game Screen */
                },
                onPlayChallengeClick = {
                    /* TODO: Navigate to Challenge Game Screen */
                },
                onLoginClick = {
                    // This triggers when the user taps the Login button on the Home screen
                    navController.navigate("login")
                }
            )
        }

        // Route 2: The Login Screen
        composable("login") {
            LoginScreen(
                onNavigateToSignup = {
                    // When the signup text is clicked, navigate to the signup route
                    navController.navigate("signup")
                },
                onLoginClick = { email, password ->
                    /* TODO: API call to authenticate user */
                    // Once authenticated, you might want to pop back to home:
                    // navController.popBackStack("home", inclusive = false)
                }
            )
        }

        // Route 3: The Signup Screen
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