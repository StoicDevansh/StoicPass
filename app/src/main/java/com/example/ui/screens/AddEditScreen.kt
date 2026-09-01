package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.data.PasswordEntry
import com.example.ui.PasswordViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    viewModel: PasswordViewModel,
    passwordId: Int?,
    onNavigateBack: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    
    var name by remember { mutableStateOf("") }
    var url by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(passwordId) {
        if (passwordId != null) {
            val entry = viewModel.getPasswordById(passwordId)
            entry?.let {
                name = it.name
                url = it.url
                email = it.email
                note = it.note
                password = it.passwordValue
            }
        }
    }

    val isFormValid = name.isNotBlank() && password.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (passwordId == null) "New Password" else "Edit Password") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (passwordId != null) {
                        IconButton(onClick = {
                            viewModel.delete(passwordId)
                            onNavigateBack()
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name (Required)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = name.isBlank() && name.isNotEmpty()
            )
            
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email / Username") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password (Required)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Row {
                        IconButton(onClick = { 
                            password = generatePassword() 
                            isPasswordVisible = true
                        }) {
                            Icon(Icons.Default.Refresh, contentDescription = "Generate Password")
                        }
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                            )
                        }
                    }
                }
            )
            
            OutlinedTextField(
                value = url,
                onValueChange = { url = it },
                label = { Text("URL / Website") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            
            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Notes") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 5
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = {
                    if (isFormValid) {
                        val entry = PasswordEntry(
                            id = passwordId ?: 0,
                            name = name,
                            url = url,
                            email = email,
                            note = note,
                            passwordValue = password
                        )
                        viewModel.insert(entry)
                        onNavigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = isFormValid,
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Save")
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

private fun generatePassword(length: Int = 16): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9') + listOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}
