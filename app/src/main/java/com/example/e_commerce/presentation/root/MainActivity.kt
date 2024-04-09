package com.example.e_commerce.presentation.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.e_commerce.presentation.navigation.AppBottomNavigation
import com.example.e_commerce.presentation.navigation.AppNavigation
import com.example.e_commerce.presentation.navigation.BottomNavigationDestinations
import com.example.e_commerce.presentation.theme.ECommerceTheme
import com.example.e_commerce.presentation.ui.screens.DetailScreen
import com.example.e_commerce.presentation.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.e_commerce.R

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ECommerceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ECommerceApp()
                }
            }
        }
    }
}

@Composable
fun ECommerceApp() {
    val navController = rememberNavController()
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationDestinations.Home,
            BottomNavigationDestinations.Search,
            BottomNavigationDestinations.Cart,
            BottomNavigationDestinations.Profile
        )
    }

    Box {
        AppNavigation(navController)
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            AppBottomNavigation(
                navController = navController,
                navigationItems = bottomNavigationItems
            )
        }
    }
}