package com.example.inovimap.feature.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.inovimap.navigation.map.MapRoute

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = context) {
        viewModel.events.collect { event ->
            when (event) {
                is LoginViewModel.Event.NavigateToMap -> {
                    navController.navigate(
                        MapRoute
                    )
                }
                is LoginViewModel.Event.ShowMessage -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "InoviMap"
            )
            TextField(
                value = state.user,
                label = {
                    Text(
                        text = "Usuario"
                    )
                },
                onValueChange = { user ->
                    viewModel.setUser(user)
                }
            )
            TextField(
                value = state.password,
                label = {
                    Text(
                        text = "Contraseña"
                    )
                },
                onValueChange = { password ->
                    viewModel.setPassword(password)
                }
            )
            Button(
                onClick = {
                    viewModel.navigateToMap()
                }
            ) {
                Text(
                    text = "Iniciar Sesión"
                )
            }
            Text(
                text = "Todos los derechos reservados 2025"
            )
        }
    }
}