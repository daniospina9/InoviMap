package com.example.inovimap.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inovimap.feature.login.LoginScreen
import com.example.inovimap.feature.map.MapScreen
import com.example.inovimap.navigation.login.LoginRoute
import com.example.inovimap.navigation.map.MapRoute

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoute
    ) {
        composable<LoginRoute> {
            LoginScreen(
                navController = navController
            )
        }
        composable<MapRoute> {
            MapScreen()
        }
    }
}