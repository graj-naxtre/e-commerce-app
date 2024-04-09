package com.example.e_commerce.presentation.navigation.navgraph.profile

sealed class ProfileRoutes(val route: String, val name: String) {
    data object ProfileScreen: ProfileRoutes(route = "profile", name = "Profile")
}