package com.example.week3.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week3.R

@Composable
fun BottomBar(
    selectedTab: String,
    onItemSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        BottomBarItem(
            icon = R.drawable.housesimple,
            label = "Home",
            isSelected = selectedTab == "home"
        ) {
            onItemSelected("home")
        }

        BottomBarItem(
            icon = R.drawable.listmagnifyingglass,
            label = "Purchase",
            isSelected = selectedTab == "purchase"
        ) {
            onItemSelected("purchase")
        }

        BottomBarItem(
            icon = R.drawable.heartstraight,
            label = "Wishlist",
            isSelected = selectedTab == "wishlist"
        ) {
            onItemSelected("wishlist")
        }

        BottomBarItem(
            icon = R.drawable.bagsimple,
            label = "Cart",
            isSelected = selectedTab == "cart"
        ) {
            onItemSelected("cart")
        }

        BottomBarItem(
            icon = R.drawable.user,
            label = "Profile",
            isSelected = selectedTab == "profile"
        ) {
            onItemSelected("profile")
        }
    }
}

@Composable
fun BottomBarItem(
    icon: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = if (isSelected) Color.Black else Color.Gray
        )

        Text(
            text = label,
            color = if (isSelected) Color.Black else Color.Gray,
            fontSize = 12.sp
        )
    }
}