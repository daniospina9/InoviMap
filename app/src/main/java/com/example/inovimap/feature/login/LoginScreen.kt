package com.example.inovimap.feature.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LoginScreen(
viewModel: LoginViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "InoviMap"
            )
            TextField(
                value = state.user,
                onValueChange = { user ->
                    viewModel.setUser(user)
                }
            )
            TextField(
                value = state.password,
                onValueChange = { password ->
                    viewModel.setPassword(password)
                }
            )
            Button(
                onClick = {}
            ) {
                Text(
                    text = "Iniciar Sesión"
                )
            }
        }
    }
}