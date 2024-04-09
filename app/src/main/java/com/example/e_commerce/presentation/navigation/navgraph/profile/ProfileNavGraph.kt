package com.example.e_commerce.presentation.navigation.navgraph.profile

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.e_commerce.presentation.navigation.homeNavigation
import com.example.e_commerce.presentation.navigation.navgraph.home.HomeRoutes
import com.example.e_commerce.presentation.navigation.profileNavigation
import com.example.e_commerce.presentation.ui.screens.HomeScreen
import com.example.e_commerce.presentation.ui.screens.ProfileScreen

fun NavGraphBuilder.profileNavGraph(
    navController: NavHostController,
    startDestination: String = ProfileRoutes.ProfileScreen.route
) {
    navigation(startDestination = startDestination, route = profileNavigation){
        composable(route = ProfileRoutes.ProfileScreen.route, enterTransition = { EnterTransition.None}, exitTransition = { ExitTransition.None}){
            ProfileScreen()
        }
    }
}