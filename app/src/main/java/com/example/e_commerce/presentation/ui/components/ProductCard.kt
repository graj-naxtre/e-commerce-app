package com.example.e_commerce.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.e_commerce.R
import com.example.e_commerce.data.models.ProductResult

@Composable
fun ProductCard(productData: ProductResult, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val painter = remember {
        ImageRequest.Builder(context)
            .data(productData.image)
            .crossfade(true)
            .build()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .then(remember {
                Modifier.clickable { onClick() }
            })
    ) {
        //image
        ElevatedCard(
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = modifier
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

            Box(modifier = Modifier
                .fillMaxSize()
                .zIndex(5f), contentAlignment = Alignment.TopEnd) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.outline_star_outline_24),
                    contentDescription = "dislike",
                    tint = Color.DarkGray,
                    modifier = Modifier.padding(end = 20.dp, top = 20.dp)
                )
            }
        }
        //name
        Text(text = "${productData.title}", overflow = TextOverflow.Ellipsis)
        //price
        Text(text = "$${productData.price}", fontWeight = FontWeight.SemiBold)
    }
}