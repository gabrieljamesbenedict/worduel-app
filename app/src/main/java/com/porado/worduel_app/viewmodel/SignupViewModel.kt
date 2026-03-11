package com.porado.worduel_app.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porado.worduel_app.data.RetrofitProvider
import com.porado.worduel_app.data.api.UsernamePassword
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {

    // Holds the UI state (loading, success, or error messages)
    var signupStatus by mutableStateOf("")
        private set

    fun performSignup(user: String, pass: String, onSuccess: () -> Unit) {
        // Basic validation
        if (user.isBlank() || pass.isBlank()) {
            signupStatus = "Username and password cannot be empty."
            return
        }

        signupStatus = "Creating account..."

        viewModelScope.launch {
            try {
                val credentials = UsernamePassword(user, pass)

                // Call the Spring Boot backend
                RetrofitProvider.authApi.register(credentials)

                signupStatus = "Account created successfully!"

                // Trigger navigation (e.g., to the login screen)
                onSuccess()

            } catch (e: Exception) {
                // Catches HTTP errors (like 409 Conflict if username exists) or network issues
                signupStatus = "Signup failed: ${e.message}"
            }
        }
    }
}