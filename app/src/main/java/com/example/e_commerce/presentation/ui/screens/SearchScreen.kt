package com.example.e_commerce.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.e_commerce.R
import com.example.e_commerce.data.models.ProductResult
import com.example.e_commerce.presentation.ui.components.TopBarLite
import com.example.e_commerce.presentation.viewmodels.HomeState
import com.example.e_commerce.presentation.viewmodels.HomeViewModel

@Composable
fun SearchScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val singleProductState = viewModel.singleProductState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getSingleProduct(1)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 15.dp, end = 15.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TopBarLite(title = "Search")
        SearchBar()
        Text(text = "Items you may like", fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
        when (val singleProduct = singleProductState.value) {
            HomeState.LOADING -> {
                CircularProgressIndicator()
            }

            is HomeState.SUCCESS<*> -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.verticalScroll(
                        rememberScrollState()
                    )
                ) {
                    repeat(10) {
                        ItemYouMayLike(product = singleProduct.data as ProductResult)
                    }
                }
            }

            is HomeState.FAILURE -> {
                Text(text = singleProduct.message)
            }

            HomeState.START -> {
                Text(text = "Welcome")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.search_24),
                contentDescription = "search icon"
            )
        },
        placeholder = { Text(text = "Search", fontSize = 16.sp) },
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Unspecified,
            focusedIndicatorColor = Color.Unspecified,
            unfocusedContainerColor = colorResource(id = R.color.grey),
            focusedContainerColor = colorResource(id = R.color.grey),
        )
    )
}

@Composable
fun ItemYouMayLike(product: ProductResult) {
    val context = LocalContext.current
    val painter = remember {
        ImageRequest.Builder(context).data(product.image).crossfade(true).build()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.grey), shape = RoundedCornerShape(4.dp))
            .padding(end = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ElevatedCard(
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.size(80.dp)
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = painter,
                    contentDescription = "Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.clip(
                        RoundedCornerShape(10.dp)
                    )
                )
            }
        }
        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.weight(1f)) {
            Text(text = product.title, overflow = TextOverflow.Ellipsis, maxLines = 1)
            Text(text = "⭐⭐⭐⭐⭐")
        }
        Icon(imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = "right arrow")
    }
}