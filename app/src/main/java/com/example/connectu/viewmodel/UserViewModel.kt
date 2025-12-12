package com.example.connectu.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connectu.data.UserDatabase
import com.example.connectu.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private lateinit var repo: UserRepository

    // ---------------------------
    // STATE
    // ---------------------------
    private val _loginResult = MutableStateFlow<String?>(null)
    val loginResult: StateFlow<String?> = _loginResult

    private val _registerResult = MutableStateFlow<String?>(null)
    val registerResult: StateFlow<String?> = _registerResult

    private val _currentUserUsername = MutableStateFlow("")
    val currentUserUsername: StateFlow<String> = _currentUserUsername

    // ---------------------------
    // INITIALIZE DATABASE
    // ---------------------------
    fun initialize(context: Context) {
        if (!::repo.isInitialized) {
            val dao = UserDatabase.getDatabase(context).userDao()
            repo = UserRepository(dao)
        }
    }

    // ---------------------------
    // LOGIN
    // ---------------------------
    fun login(username: String, password: String) {
        if (!::repo.isInitialized) return

        viewModelScope.launch {
            val user = repo.getUserByUsername(username)

            if (user != null && user.password == password) {
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
    fun register(username: String, password: String) {
        if (!::repo.isInitialized) return

        viewModelScope.launch {
            try {
                repo.register(username, password)
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
