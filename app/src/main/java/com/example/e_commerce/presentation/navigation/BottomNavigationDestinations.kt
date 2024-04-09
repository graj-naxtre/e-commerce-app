package com.example.e_commerce.presentation.navigation

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import com.example.e_commerce.R

const val homeNavigation = "HomeNavigationRoute"
const val searchNavigation = "SearchNavigationRoute"
const val cartNavigation = "CartNavigationRoute"
const val profileNavigation = "ProfileNavigationRoute"

sealed class BottomNavigationDestinations(
    val title: String,
    val icon: Int,
    val route: String
) {
    data object Home : BottomNavigationDestinations(
        title = "Home",
        icon = R.drawable.home_24,
        route = homeNavigation
    )
    data object Search : BottomNavigationDestinations(
        title = "Search",
        icon = R.drawable.search_24,
        route = searchNavigation
    )
    data object Cart : BottomNavigationDestinations(
        title = "Cart",
       icon = R.drawable.shopping_cart_24,
        route = cartNavigation
    )

    data object Profile : BottomNavigationDestinations(
        title = "Profile",
        icon = R.drawable.person_24,
        route = profileNavigation
    )
}