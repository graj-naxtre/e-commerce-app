package com.example.e_commerce.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.e_commerce.data.models.ProductResult
import com.example.e_commerce.presentation.navigation.navgraph.home.HomeScreenNavigationEvent
import com.example.e_commerce.presentation.ui.components.CategoryCard
import com.example.e_commerce.presentation.ui.components.ProductCard
import com.example.e_commerce.presentation.ui.components.TopBar
import com.example.e_commerce.presentation.viewmodels.HomeState
import com.example.e_commerce.presentation.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigationAction: (HomeScreenNavigationEvent) -> Unit
) {
    val categoryState by viewModel.categoryState.collectAsState()
    val categoryProductState by viewModel.categoryProductState.collectAsState()
    var categorySelected by remember { mutableStateOf("All") }

    LaunchedEffect(Unit) {
        viewModel.getAllCategories()
    }

    LaunchedEffect(categorySelected) {
        viewModel.getAllFromCategory(categorySelected)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp)
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
    ) {
        TopBar()

        Row (modifier = Modifier.padding(vertical = 15.dp)){
            Text(
                text = "Find Your Style",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                modifier = Modifier.offset(20.dp)
            )
        }

        //categories
        when (val value = categoryState) {
            is HomeState.SUCCESS<*> -> {
                val categoryList = value.data as List<String>
                LazyRow(
                    contentPadding = PaddingValues(start = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item {
                        CategoryCard(
                            item = "All",
                            onClick = { categorySelected = "All" },
                            containerColor = if (categorySelected == "All") Color.Black else Color.White,
                            contentColor = if (categorySelected == "All") Color.White else Color.Black
                        )
                    }
                    items(items = categoryList) { item ->
                        CategoryCard(
                            item = item,
                            onClick = { categorySelected = item },
                            containerColor = if (categorySelected == item) Color.Black else Color.White,
                            contentColor = if (categorySelected == item) Color.White else Color.Black
                        )
                    }
                }
            }

            else -> {
                Text(text = "Loading...")
            }
        }

        //category products
        when (val value = categoryProductState) {
            is HomeState.SUCCESS<*> -> {
                val categoryList = value.data as List<ProductResult>
                LazyRow(
                    contentPadding = PaddingValues(start = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(items = categoryList) { item ->
                        ProductCard(
                            productData = item,
                            onClick = {
                                navigationAction(HomeScreenNavigationEvent.isProductClicked(item.id))
                            },
                            modifier = Modifier.size(300.dp))
                    }
                }
            }

            is HomeState.FAILURE -> {
                Text(text = value.message)
            }

            HomeState.LOADING -> {
                Text(text = "Loading...")
            }

            HomeState.START -> {
                Text(text = "Welcome !")
            }
        }

        // popular items
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
            Text("Popular items", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "See All",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Red
            )
        }
        when (val value = categoryProductState) {
            is HomeState.SUCCESS<*> -> {
                val categoryList = value.data as List<ProductResult>
                LazyRow(
                    contentPadding = PaddingValues(start = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(items = categoryList) { item ->
                        ProductCard(
                            productData = item,
                            onClick = {
                                navigationAction(HomeScreenNavigationEvent.isProductClicked(item.id))
                            },
                            modifier = Modifier.size(200.dp))
                    }
                }
            }

            is HomeState.FAILURE -> {
                Text(text = value.message)
            }

            HomeState.LOADING -> {
                Text(text = "Loading...")
            }

            HomeState.START -> {
                Text(text = "Welcome !")
            }
        }
    }
}