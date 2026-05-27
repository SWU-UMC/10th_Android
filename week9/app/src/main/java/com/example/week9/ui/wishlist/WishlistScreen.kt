package com.example.week9.ui.wishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week9.model.ProductUiModel
import com.example.week9.ui.component.ProductGridCard

private val White = Color.White
private val Black = Color(0xFF111111)

@Composable
fun WishlistScreen(
    products: List<ProductUiModel>,
    onProductClick: (ProductUiModel) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .windowInsetsPadding(WindowInsets.statusBars),
    ) {
        Text(
            text = "위시리스트",
            color = Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 28.dp, top = 58.dp, end = 28.dp),
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 28.dp, top = 34.dp, end = 28.dp, bottom = 24.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(10.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(26.dp),
        ) {
            items(
                items = products,
                key = { product -> product.id },
            ) { product ->
                ProductGridCard(
                    product = product,
                    isWishlisted = true,
                    onClick = { onProductClick(product) },
                    onWishClick = {},
                    showHeart = false,
                )
            }
        }
    }
}


