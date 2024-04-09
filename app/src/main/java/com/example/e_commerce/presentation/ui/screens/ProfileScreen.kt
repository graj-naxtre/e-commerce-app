package com.example.e_commerce.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_commerce.R
import com.example.e_commerce.presentation.ui.components.ProfileOptionCard
import com.example.e_commerce.presentation.ui.components.TopBarLite

@Composable
fun ProfileScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 15.dp)
    ) {
        TopBarLite(title = "Profile")
        Row(modifier = Modifier.padding(vertical = 20.dp)) {
            UserCard()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            Text(text = "Settings", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        }
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            profileOptions.forEach {
                ProfileOptionCard(it)
            }
        }
    }
}

data class ProfileData(val title: String, val icon: ImageVector, val route: String)

val profileOptions = listOf(
    ProfileData("Personal Data", Icons.Outlined.Notifications, "personal_data"),
    ProfileData("Notifications", Icons.Outlined.Notifications, "notifications"),
    ProfileData("Tracking Order", Icons.Outlined.Notifications, "tracking_order"),
    ProfileData("Order Status", Icons.Outlined.Notifications, "order_status"),
    ProfileData("Language", Icons.Outlined.Notifications, "language"),
    ProfileData("FAQs", Icons.Outlined.Notifications, "faqs"),
)

@Composable
fun UserCard() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.grey), RoundedCornerShape(5.dp))
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Card(shape = CircleShape, backgroundColor = Color.Blue, modifier = Modifier.size(70.dp)) {
            Text(text = "")
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "User Name", fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
            Text(text = "User type", fontWeight = FontWeight.Light)
        }
        Icon(imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = "icon")
    }
}