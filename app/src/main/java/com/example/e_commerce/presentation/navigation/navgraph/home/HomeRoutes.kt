package com.example.e_commerce.presentation.navigation.navgraph.home

sealed class HomeRoutes(val route: String, val name: String) {
    data object HomeScreen: HomeRoutes(route = "home", name = "Home")

    data object DetailScreen : HomeRoutes(route = "detail", name = "Detail"){
        fun getHomeDetailRoute() :String {
            return "detail/{detail_route}"
        }

        fun getHomeDetailRouteWithArgs(detailRoute : String) :String{
            return "detail/$detailRoute"
        }
    }

    data object ProductScreen: HomeRoutes(route = "product", name = "Product"){
        fun getHomeProductRoute() : String {
            return "product/{productId}"
        }
        fun getHomeProductRouteWithArgs(productId : Int) : String {
            return "product/$productId"
        }
    }
}