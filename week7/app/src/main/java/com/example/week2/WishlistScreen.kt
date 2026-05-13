package com.example.week2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week2.ui.theme.NikeBlack

@Composable
fun WishlistScreen(onProductClick: (Product) -> Unit) {
    var wishlistProducts by remember {
        mutableStateOf(
            listOf(
                Product(4, "Nike Training Ankle Socks", "6 Pairs", "US$16", R.drawable.img_training_ankle_socks, true),
                Product(1, "Air Jordan XXXVI", "Basketball Shoes", "US$185", R.drawable.img_air_jordan_xxxvi, true)
            )
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "위시리스트",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = NikeBlack,
            modifier = Modifier.padding(20.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(wishlistProducts) { product ->
                ProductItem(
                    product = product,
                    onItemClick = onProductClick,
                    onWishlistClick = { clickedProduct ->
                        wishlistProducts = wishlistProducts.filter { it.id != clickedProduct.id }
                    }
                )
            }
        }
    }
}
