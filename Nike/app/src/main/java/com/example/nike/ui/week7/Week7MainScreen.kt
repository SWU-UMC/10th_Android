package com.example.nike.ui.week7

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nike.data.model.ProductDummyData

@Composable
fun Week7MainScreen() {
    var selectedDestination by rememberSaveable { mutableStateOf(Week7Destination.Home) }
    var products by remember {
        mutableStateOf(ProductDummyData.getProducts().map { it.copy(isLiked = false) })
    }
    val onToggleWishlist: (Int) -> Unit = { productId ->
        products = products.map { product ->
            if (product.id == productId) product.copy(isLiked = !product.isLiked) else product
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Week7Colors.Background,
        bottomBar = {
            Week7BottomBar(
                selectedDestination = selectedDestination,
                onDestinationSelected = { selectedDestination = it },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when (selectedDestination) {
                Week7Destination.Home -> Week7HomeScreen(
                    products = products,
                    onToggleWishlist = onToggleWishlist,
                )
                Week7Destination.Shop -> Week7ShopScreen(
                    products = products,
                    onToggleWishlist = onToggleWishlist,
                )
                Week7Destination.Wishlist -> Week7WishlistScreen(
                    products = products.filter { it.isLiked },
                    onToggleWishlist = onToggleWishlist,
                )
                Week7Destination.Cart -> Week7CartScreen(
                    onOrderClick = { selectedDestination = Week7Destination.Shop },
                )
                Week7Destination.Profile -> Week7ProfileScreen()
            }
        }
    }
}

@Composable
private fun Week7BottomBar(
    selectedDestination: Week7Destination,
    onDestinationSelected: (Week7Destination) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .height(76.dp)
            .padding(horizontal = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Week7Destination.entries.forEach { destination ->
            val selected = destination == selectedDestination
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { onDestinationSelected(destination) }
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = destination.iconResId),
                    contentDescription = destination.label,
                    modifier = Modifier.size(24.dp),
                    alpha = if (selected) 1f else 0.42f,
                )
                Text(
                    text = destination.label,
                    color = if (selected) Week7Colors.TextPrimary else Week7Colors.TextMuted,
                    fontSize = 11.sp,
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                    modifier = Modifier.padding(top = 5.dp),
                )
            }
        }
    }
}