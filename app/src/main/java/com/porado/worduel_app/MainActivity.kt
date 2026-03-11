package com.porado.worduel_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.porado.worduel_app.data.api.UsernamePassword
import com.porado.worduel_app.data.service.AuthService
import kotlinx.coroutines.launch


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
                    navController.navigate("login")
                }
            )
        }

        // Route 2: The Login Screen
        composable("login") {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate("home") {
                        // Clear the backstack so the user can't press 'back' to go to the login screen
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // Route 3: The Signup Screen
        composable("signup") {
            SignupScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onSignupSuccess = {
                    // Send them back to login after successful creation
                    navController.navigate("login") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            )
        }
    }
}