package com.example.e_commerce.presentation.navigation.navgraph.cart

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.e_commerce.presentation.navigation.cartNavigation
import com.example.e_commerce.presentation.navigation.navgraph.profile.ProfileRoutes
import com.example.e_commerce.presentation.navigation.profileNavigation
import com.example.e_commerce.presentation.ui.screens.CartScreen
import com.example.e_commerce.presentation.ui.screens.ProfileScreen

fun NavGraphBuilder.cartNavGraph(
    navController: NavHostController,
    startDestination: String = CartRoutes.CartScreen.route
) {
    navigation(startDestination = startDestination, route = cartNavigation){
        composable(route = CartRoutes.CartScreen.route, enterTransition = { EnterTransition.None}, exitTransition = { ExitTransition.None}){
            CartScreen()
        }
    }
}