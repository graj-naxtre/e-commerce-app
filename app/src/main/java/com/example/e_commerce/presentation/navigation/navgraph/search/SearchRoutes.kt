package com.example.e_commerce.presentation.navigation.navgraph.search

sealed class SearchRoutes(val route: String, val name: String) {
    data object SearchScreen: SearchRoutes(route = "search", name = "Search")
}