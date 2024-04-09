package com.example.e_commerce.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.e_commerce.presentation.ui.screens.ProfileData
import com.example.e_commerce.R

@Composable
fun ProfileOptionCard(profileData: ProfileData) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.grey), RoundedCornerShape(5.dp))
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Icon(imageVector = profileData.icon, contentDescription = "icon")
        Text(text = profileData.title, modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = "icon")
    }
}