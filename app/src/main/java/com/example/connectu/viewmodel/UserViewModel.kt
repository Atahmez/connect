package com.example.connectu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.connectu.data.UserDatabase
import com.example.connectu.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = UserDatabase.getDatabase(application).userDao()
    private val repo = UserRepository(dao)

    private val _loginResult = MutableStateFlow<String?>(null)
    val loginResult: StateFlow<String?> = _loginResult

    private val _registerResult = MutableStateFlow<String?>(null)
    val registerResult: StateFlow<String?> = _registerResult

    private val _currentUserDisplayName = MutableStateFlow("")
    val currentUserDisplayName: StateFlow<String> = _currentUserDisplayName

    private val _currentUserUsername = MutableStateFlow("")
    val currentUserUsername: StateFlow<String> = _currentUserUsername

    // ---------------------------
    // LOGIN
    // ---------------------------
    fun login(username: String, password: String) {
        viewModelScope.launch {
            val user = repo.loginUser(username, password)

            if (user != null) {
                _currentUserDisplayName.value = user.displayName
                _currentUserUsername.value = user.username
                _loginResult.value = "success"
            } else {
                _loginResult.value = "Invalid username or password"
            }
        }
    }

    // ---------------------------
    // REGISTER
    // ---------------------------
    fun register(username: String, password: String, displayName: String) {
        viewModelScope.launch {
            try {
                repo.registerUser(username, password, displayName)
                _registerResult.value = "success"
            } catch (e: Exception) {
                _registerResult.value = "Error creating account"
            }
        }
    }

    fun clearLoginState() {
        _loginResult.value = null
    }

    fun clearRegisterState() {
        _registerResult.value = null
    }
}
