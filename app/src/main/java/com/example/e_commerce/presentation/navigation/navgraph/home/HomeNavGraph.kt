package com.example.e_commerce.presentation.navigation.navgraph.home

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.e_commerce.presentation.navigation.homeNavigation
import com.example.e_commerce.presentation.ui.screens.HomeScreen
import com.example.e_commerce.presentation.ui.screens.ProductScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    startDestination: String = HomeRoutes.HomeScreen.route
) {
    navigation(startDestination = startDestination, route = homeNavigation) {
        composable(
            route = HomeRoutes.HomeScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            HomeScreen(navigationAction = {
                handleHomeScreenNavigation(event = it, navController)
            })
        }

        composable(
            route = HomeRoutes.ProductScreen.getHomeProductRoute(),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            val productId = it.arguments?.getString("productId")?.toInt()
            ProductScreen(productId = productId ?: 1)
        }
    }
}

fun handleHomeScreenNavigation(event: HomeScreenNavigationEvent, navController: NavHostController) {
    when (event) {
        is HomeScreenNavigationEvent.isProductClicked -> {
            navController.navigate(HomeRoutes.ProductScreen.getHomeProductRouteWithArgs(event.productId))
        }
    }
}

sealed class HomeScreenNavigationEvent() {
    data class isProductClicked(val productId: Int) : HomeScreenNavigationEvent()
}