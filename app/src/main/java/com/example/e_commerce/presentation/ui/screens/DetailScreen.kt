package com.example.e_commerce.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.e_commerce.data.models.ProductResult
import com.example.e_commerce.presentation.ui.components.ProductCard
import com.example.e_commerce.presentation.viewmodels.HomeState
import com.example.e_commerce.presentation.viewmodels.HomeViewModel

@Composable
fun DetailScreen(viewModel: HomeViewModel) {
    val productState by viewModel.productState.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getAllProducts()
    }
    when(val value = productState){
        HomeState.START -> {
            Text(text = "Welcome")
        }

        HomeState.LOADING -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }

        is HomeState.SUCCESS<*> -> {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 200.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
               val productList = value.data as List<ProductResult>
                items(items = productList){item ->
                    ProductCard(productData = item, onClick = {})
                }
            }
        }

        is HomeState.FAILURE -> {
            Text(text = value.message)
        }
    }
}