package com.example.inovimap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.inovimap.feature.login.LoginScreen
import com.example.inovimap.ui.theme.InoviMapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InoviMapTheme {
                LoginScreen()
            }
        }
    }
}