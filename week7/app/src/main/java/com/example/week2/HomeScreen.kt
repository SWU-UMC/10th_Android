package com.example.week2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week2.ui.theme.NikeBlack
import com.example.week2.ui.theme.NikeGray

@Composable
fun HomeScreen(onProductClick: (Product) -> Unit) {
    val scrollState = rememberScrollState()

    val dummyProducts = listOf(
        Product(1, "Air Jordan XXXVI", "Basketball Shoes", "US$185", R.drawable.img_air_jordan_xxxvi, category = "Basketball Shoes"),
        Product(2, "Nike Air Force 1 '07", "Men's Shoes", "US$115", R.drawable.img_nike_air_force, category = "Men's Shoes"),
        Product(3, "Nike Everyday Plus Cushioned", "Training Socks", "US$20", R.drawable.img_nike_everyday_plus_cushioned, category = "Training Socks")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)) {
            Text(
                text = "Discover",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = NikeBlack
            )
            Text(
                text = "Thursday, November 28", // Simplified for now
                fontSize = 14.sp,
                color = NikeGray
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_homeimg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentScale = ContentScale.FillWidth
        )

        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "What's new",
                fontSize = 12.sp,
                color = NikeGray
            )
            Text(
                text = "나이키 최신 상품",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = NikeBlack
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(dummyProducts) { product ->
                ProductItem(
                    product = product,
                    onItemClick = onProductClick,
                    onWishlistClick = { /* Handle wishlist */ },
                    modifier = Modifier.width(200.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}
