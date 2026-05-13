package com.example.nike.ui.week7

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nike.data.model.Product

@Composable
fun Week7ShopScreen(
    products: List<Product>,
    onToggleWishlist: (Int) -> Unit,
) {
    var selectedTab by remember { mutableStateOf(ShopTab.Top) }
    val visibleProducts = products.filter { product ->
        when (selectedTab) {
            ShopTab.Top -> product.category == "TOP"
            ShopTab.Sale -> product.category == "SALE"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Week7Colors.Background)
            .padding(top = 24.dp),
    ) {
        Text(
            text = "구매하기",
            color = Week7Colors.TextMuted,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 24.dp),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
        ) {
            ShopTab.entries.forEach { tab ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedTab = tab }
                        .padding(vertical = 12.dp),
                ) {
                    Text(
                        text = tab.title,
                        color = if (selectedTab == tab) Week7Colors.TextPrimary else Week7Colors.TextMuted,
                        fontSize = 15.sp,
                        fontWeight = if (selectedTab == tab) FontWeight.Bold else FontWeight.Normal,
                        modifier = Modifier.padding(start = 24.dp),
                    )
                    Spacer(
                        modifier = Modifier
                            .padding(start = 24.dp, top = 10.dp)
                            .height(2.dp)
                            .fillMaxWidth(0.36f)
                            .background(if (selectedTab == tab) Week7Colors.TextPrimary else Color.Transparent),
                    )
                }
            }
        }

        ProductGrid(products = visibleProducts, onToggleWishlist = onToggleWishlist)
    }
}

@Composable
private fun ProductGrid(
    products: List<Product>,
    onToggleWishlist: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        products.chunked(2).forEach { rowProducts ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                rowProducts.forEach { product ->
                    Week7ProductCard(
                        product = product,
                        onHeartClick = { onToggleWishlist(product.id) },
                        modifier = Modifier.weight(1f),
                    )
                }
                if (rowProducts.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

private enum class ShopTab(val title: String) {
    Top("Top"),
    Sale("Sale"),
}