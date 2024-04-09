package com.example.e_commerce.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.e_commerce.data.models.ProductResult
import com.example.e_commerce.presentation.ui.components.TopBarLite
import com.example.e_commerce.presentation.viewmodels.HomeState
import com.example.e_commerce.presentation.viewmodels.HomeViewModel

@Composable
fun CartScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val singleProductState = viewModel.singleProductState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getSingleProduct(1)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 15.dp, end = 15.dp, bottom = 100.dp )
    ) {
        TopBarLite(title = "My Cart")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Promo codes and vouchers",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "")
        }
        when (val singleProduct = singleProductState.value) {
            HomeState.LOADING -> {
                CircularProgressIndicator()
            }

            is HomeState.SUCCESS<*> -> {
                CartCard(product = singleProduct.data as ProductResult)
            }

            is HomeState.FAILURE -> {
                Text(text = singleProduct.message)
            }

            HomeState.START -> {
                Text(text = "Welcome")
            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            CartSummary()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCart() {
    ActionControls()
}

@Composable
fun CartCard(product: ProductResult) {
    val context = LocalContext.current
    val painter = remember {
        ImageRequest.Builder(context).data(product.image).crossfade(true).build()
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ElevatedCard(
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.size(width = 200.dp, height = 150.dp)
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

        Column(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "Casual", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "Casual jeans shoes", fontSize = 16.sp)
            Text(text = "size: XL")
            Text(text = "$ 134.98", fontWeight = FontWeight.Bold)
            ActionControls()
        }
    }
}

@Composable
fun ActionControls() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier
                .background(Color.LightGray, RoundedCornerShape(5.dp))
        ) {
            Icon(imageVector = Icons.Outlined.Clear, contentDescription = "")
            Text(text = "1", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Icon(imageVector = Icons.Outlined.Clear, contentDescription = "")
        }
        Icon(imageVector = Icons.Outlined.Clear, contentDescription = "clear")
    }
}

@Composable
fun CartSummary() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        SummaryRow(title = "Subtotal", price = "134.94")
        SummaryRow(title = "Fee & Delivery", price = "10")
        SummaryRow(title = "Total", price = "144.94")
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            ),
            contentPadding = PaddingValues(15.dp)
        ) {
            Text(text = "Checkout", color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun SummaryRow(title: String, price: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Text(text = "$ $price")
    }
}