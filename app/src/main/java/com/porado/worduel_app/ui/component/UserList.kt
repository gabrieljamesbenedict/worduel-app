package com.porado.worduel_app.ui.component

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.porado.worduel_app.data.api.User
import com.porado.worduel_app.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// -------------------- ViewModel --------------------

class UserListViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class UserListViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadUsers() {
        viewModelScope.launch {
            _loading.value = true
            _errorMessage.value = null
            try {
                _users.value = repository.getAllUsers()
            } catch (e: Exception) {
                // Catch network errors, HTTP errors, etc.
                _errorMessage.value = "Failed to load users: ${e.message}"
                _users.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
    }
}

// -------------------- Composables --------------------

@Composable
fun UserList() {
    // Create repository manually
    val repository = UserRepository // or UserRepository(RetrofitProvider.api) depending on your setup

    val viewModel: UserListViewModel = viewModel(
        factory = UserListViewModelFactory(repository)
    )

    val users by viewModel.users.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        // Retry button
        Button(onClick = { viewModel.loadUsers() }, modifier = Modifier.fillMaxWidth()) {
            Text("Load Users")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Loading indicator
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }

        // Error message
        errorMessage?.let { msg ->
            Text(text = msg, color = MaterialTheme.colorScheme.error)
        }

        // User list
        if (!loading && users.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(users) { user ->
                    UserRow(user)
                    Divider()
                }
            }
        }

        // Empty state
        if (!loading && users.isEmpty() && errorMessage == null) {
            Text("No users found.")
        }
    }
}

@Composable
fun UserRow(user: User) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "ID: ${user.userId ?: "N/A"}")
        Text(text = "Username: ${user.username}")
        Text(text = "Password: ${user.password}")
        Text(text = "Role: ${user.role}")
    }
}
