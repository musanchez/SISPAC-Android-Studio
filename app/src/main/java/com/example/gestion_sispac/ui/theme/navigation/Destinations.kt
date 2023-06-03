package com.example.gestion_sispac.ui.theme.navigation

sealed class Destinations(
    val route: String

) {
    object CatalogScreen: Destinations("catalog")
    object OptionScreen: Destinations("options")
    object LoginScreen: Destinations("login")
}