package com.example.gestion_sispac.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestion_sispac.ui.theme.screen.CatalogScreen
import com.example.gestion_sispac.ui.theme.screen.FormLogin
import com.example.gestion_sispac.ui.theme.screen.OptionScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = /*Destinations.LoginScreen.route*/ Destinations.CatalogScreen.route) {
        composable(Destinations.LoginScreen.route) {
            FormLogin(navController = navController)
        }
        composable(Destinations.OptionScreen.route) {
            OptionScreen(navController = navController)
        }
        composable(Destinations.CatalogScreen.route) {
            CatalogScreen(navController = navController)
        }
    }
}