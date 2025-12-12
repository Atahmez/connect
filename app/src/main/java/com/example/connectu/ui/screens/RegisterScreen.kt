package com.example.connectu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.connectu.viewmodel.UserViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    userViewModel: UserViewModel
) {
    val context = LocalContext.current

    // 🔴 REQUIRED: initialize Room + repository
    LaunchedEffect(Unit) {
        userViewModel.initialize(context)
    }

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val registerResult by userViewModel.registerResult.collectAsState()

    // 🔁 React to register result
    LaunchedEffect(registerResult) {
        if (registerResult == "success") {
            userViewModel.clearRegisterState()
            navController.popBackStack() // go back to login
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Create Account",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        if (registerResult != null && registerResult != "success") {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = registerResult ?: "",
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (username.isNotBlank() &&
                    password.isNotBlank() &&
                    password == confirmPassword
                ) {
                    userViewModel.register(username, password)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Register", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            userViewModel.clearRegisterState()
            navController.popBackStack()
        }) {
            Text("Back to Login")
        }
    }
}
