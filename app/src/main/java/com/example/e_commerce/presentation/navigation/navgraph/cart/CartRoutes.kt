package com.example.e_commerce.presentation.navigation.navgraph.cart

sealed class CartRoutes(val route: String, val name: String) {
    data object CartScreen: CartRoutes(route = "cart", name = "Cart")
}