package com.example.e_commerce.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.e_commerce.presentation.navigation.navgraph.cart.cartNavGraph
import com.example.e_commerce.presentation.navigation.navgraph.home.homeNavGraph
import com.example.e_commerce.presentation.navigation.navgraph.profile.profileNavGraph
import com.example.e_commerce.presentation.navigation.navgraph.search.searchNavGraph

@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = homeNavigation,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        homeNavGraph(navController)
        searchNavGraph(navController)
        cartNavGraph(navController)
        profileNavGraph(navController)
    }
}