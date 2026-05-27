package com.example.week9.ui.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week9.model.ProductUiModel
import com.example.week9.ui.component.ProductGridCard

private val White = Color.White
private val Black = Color(0xFF111111)
private val GrayText = Color(0xFF767676)

@Composable
fun ShopScreen(
    products: List<ProductUiModel>,
    wishlistedIds: List<Int>,
    onProductClick: (ProductUiModel) -> Unit,
    onWishClick: (ProductUiModel) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .windowInsetsPadding(WindowInsets.statusBars),
    ) {
        Row(
            modifier = Modifier.padding(start = 28.dp, top = 62.dp),
            horizontalArrangement = Arrangement.spacedBy(38.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            ShopTab(text = "전체", selected = true)
            ShopTab(text = "Tops & T-Shirts", selected = false)
            ShopTab(text = "sale", selected = false)
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 28.dp, top = 16.dp, end = 28.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(34.dp),
        ) {
            items(
                items = products,
                key = { product -> product.id },
            ) { product ->
                ProductGridCard(
                    product = product,
                    isWishlisted = product.id in wishlistedIds,
                    onClick = { onProductClick(product) },
                    onWishClick = { onWishClick(product) },
                    showHeart = true,
                )
            }
        }
    }
}

@Composable
private fun ShopTab(text: String, selected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = text,
            color = if (selected) Black else GrayText,
            fontSize = 16.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
        )
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .padding(top = 14.dp)
                .size(width = 82.dp, height = 2.dp)
                .background(if (selected) Black else Color.Transparent),
        )
    }
}


