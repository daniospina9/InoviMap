package com.example.inovimap.feature.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.inovimap.navigation.map.MapRoute
import com.example.inovimap.ui.theme.BackgroundColor
import com.example.inovimap.ui.theme.BackgroundTextFieldColor
import com.example.inovimap.ui.theme.LoginButtonColor

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
                        MapRoute(
                            latitude = event.latitude,
                            longitude = event.longitude
                        )
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
                .background(BackgroundColor)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(0.26f))
            Text(
                text = "InoviMap",
                color = Color.White,
                fontSize = 27.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.weight(0.26f))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                value = state.user,
                label = {
                    Text(
                        text = "User",
                        color = Color.White
                    )
                },
                onValueChange = { user ->
                    viewModel.setUser(user)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = BackgroundTextFieldColor,
                    unfocusedContainerColor = BackgroundTextFieldColor,
                    disabledContainerColor = BackgroundTextFieldColor,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = BackgroundTextFieldColor,
                    unfocusedIndicatorColor = BackgroundTextFieldColor,
                )
            )
            Spacer(modifier = Modifier.height(18.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                value = state.password,
                label = {
                    Text(
                        text = "Password",
                        color = Color.White
                    )
                },
                onValueChange = { password ->
                    viewModel.setPassword(password)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = BackgroundTextFieldColor,
                    unfocusedContainerColor = BackgroundTextFieldColor,
                    disabledContainerColor = BackgroundTextFieldColor,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = BackgroundTextFieldColor,
                    unfocusedIndicatorColor = BackgroundTextFieldColor,
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    viewModel.navigateToMap()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = LoginButtonColor,
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Iniciar Sesión",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(modifier = Modifier.weight(0.48f))
            Text(
                text = "Todos los derechos reservados 2025",
                color = Color.White
            )
        }
    }
}