package com.porado.worduel_app.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porado.worduel_app.data.RetrofitProvider
import com.porado.worduel_app.data.api.UsernamePassword
import com.porado.worduel_app.data.service.AuthService
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    // This state will let your UI know what's happening
    var loginStatus by mutableStateOf("")
        private set

    fun performLogin(user: String, pass: String, onSuccess: () -> Unit) {
        loginStatus = "Logging in..."

        viewModelScope.launch {
            try {
                val credentials = UsernamePassword(user, pass)
                val response = RetrofitProvider.authApi.login(credentials)

                AuthService.jwtToken = response.token
                loginStatus = "Success!"

                // Trigger the navigation callback
                onSuccess()

            } catch (e: Exception) {
                // If the backend returns a 401 Unauthorized or the server is down
                loginStatus = "Login failed: ${e.message}"
            }
        }
    }
}