package com.example.e_commerce.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.e_commerce.R
import com.example.e_commerce.data.models.ProductResult
import com.example.e_commerce.presentation.viewmodels.HomeState
import com.example.e_commerce.presentation.viewmodels.HomeViewModel

val sizeArray = listOf("S", "M", "L", "XL", "XXL")

@Composable
fun ProductScreen(productId: Int, viewModel: HomeViewModel = hiltViewModel()) {
    val singleProduct = viewModel.singleProductState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getSingleProduct(productId)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        when (val response = singleProduct.value) {
            HomeState.LOADING -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            HomeState.START -> {
                Text(text = "Welcome")
            }

            is HomeState.SUCCESS<*> -> {
                val productItem = response.data as ProductResult
                ProductContent(product = productItem)
            }

            is HomeState.FAILURE -> {
                Text(text = response.message)
            }
        }
    }
}

@Composable
fun ProductContent(product: ProductResult) {
    val context = LocalContext.current
    val painter = remember {
        ImageRequest.Builder(context)
            .data(product.image)
            .crossfade(true)
            .build()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = painter,
                contentDescription = "Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.clip(
                    RoundedCornerShape(10.dp)
                )
            )
        }
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.outline_share_24),
                    contentDescription = "share icon",
                    tint = Color.Black
                )
            }
            Row {
                Text(text = "Order status")
            }
            Row {
                Text(text = "")
            }
            Row {
                Text(text = "Color")
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "${product.price}")
                Button(onClick = { /*TODO*/ },) {
                    Text(text = "Add to Cart")
                }
            }
        }
    }
}