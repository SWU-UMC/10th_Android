package com.example.week2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.week2.ui.theme.NikeBlack
import com.example.week2.ui.theme.NikeGray

@Composable
fun PurchaseScreen(onProductClick: (Product) -> Unit) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("전체", "Tops & T-Shirts", "sale")

    val dummyProducts = listOf(
        Product(1, "Air Jordan XXXVI", "Basketball Shoes", "US$185", R.drawable.img_air_jordan_xxxvi),
        Product(2, "Nike Air Force 1 '07", "Men's Shoes", "US$115", R.drawable.img_nike_air_force),
        Product(3, "Nike Everyday Plus Cushioned", "Training Socks", "US$20", R.drawable.img_nike_everyday_plus_cushioned),
        Product(4, "Nike Training Ankle Socks", "6 Pairs", "US$16", R.drawable.img_training_ankle_socks)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.White,
            contentColor = NikeBlack,
            edgePadding = 16.dp,
            indicator = { tabPositions ->
                if (selectedTabIndex < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = NikeBlack
                    )
                }
            },
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) NikeBlack else NikeGray
                        )
                    }
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(dummyProducts) { product ->
                ProductItem(
                    product = product,
                    onItemClick = onProductClick,
                    onWishlistClick = { /* Handle wishlist */ }
                )
            }
        }
    }
}
