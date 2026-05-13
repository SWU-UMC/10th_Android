package com.example.nike.ui.week7

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.nike.R
import com.example.nike.data.model.Product

@Composable
fun Week7HomeScreen(
    products: List<Product>,
    onToggleWishlist: (Int) -> Unit,
) {
    val newProducts = products.filter { it.isNew }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Week7Colors.Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 24.dp),
    ) {
        Text(
            text = "Discover",
            color = Week7Colors.TextPrimary,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "9월 4일 목요일",
            color = Week7Colors.TextMuted,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 6.dp),
        )

        Image(
            painter = painterResource(id = R.drawable.home_banner),
            contentDescription = "Nike home banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .padding(top = 24.dp),
            contentScale = ContentScale.Crop,
        )

        Text(
            text = "What's new",
            color = Week7Colors.TextPrimary,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 24.dp),
        )
        Text(
            text = "나이키 최신 상품",
            color = Week7Colors.TextSecondary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            newProducts.take(2).forEach { product ->
                Week7ProductCard(
                    product = product,
                    onHeartClick = { onToggleWishlist(product.id) },
                    modifier = Modifier.weight(1f),
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}