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

    NavHost(navController = navController, startDestination = "login") {

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

            val scope = rememberCoroutineScope()
            LoginScreen(
                onStartClick = { nickname ->
                    navController.navigate("home")
                }
            )
        /*
            LoginScreen(
                onNavigateToSignup = {
                    navController.navigate("signup")
                },
                onLoginClick = { username, password ->
                    scope.launch {
                        AuthService.login(UsernamePassword(username, password))
                    }
                }
            ) */
        }

        // Route 3: The Signup Screen
        composable("signup") {

            val scope = rememberCoroutineScope()

            SignupScreen(
                onNavigateToLogin = {
                    // Go back to the login screen
                    navController.popBackStack()
                },
                onSignupClick = { username, password ->
                    scope.launch {
                        AuthService.register(UsernamePassword(username, password))
                    }
                }
            )
        }
    }
}