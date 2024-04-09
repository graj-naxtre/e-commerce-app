package com.example.e_commerce.presentation.navigation.navgraph.search

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.e_commerce.presentation.navigation.searchNavigation
import com.example.e_commerce.presentation.ui.screens.SearchScreen

fun NavGraphBuilder.searchNavGraph(
    navController: NavHostController,
    startDestination: String = SearchRoutes.SearchScreen.route
) {
    navigation(startDestination = startDestination, route = searchNavigation) {
        composable(route = SearchRoutes.SearchScreen.route, enterTransition = { EnterTransition.None}, exitTransition = { ExitTransition.None}) {
            SearchScreen()
        }
    }
}