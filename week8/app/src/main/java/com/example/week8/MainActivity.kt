package com.example.week8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week8.ui.theme.Week8Theme

private val White = Color.White
private val Black = Color(0xFF111111)
private val GrayText = Color(0xFF767676)
private val LightGray = Color(0xFFF5F5F5)
private val LineGray = Color(0xFFD7D7D7)
private val Orange = Color(0xFFE0642A)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week8Theme {
                Week8App()
            }
        }
    }
}

@Composable
private fun Week8App() {
    var selectedTab by rememberSaveable { mutableStateOf(BottomTab.Home) }
    var selectedProductId by rememberSaveable { mutableStateOf<Int?>(null) }
    val products = remember { week8Products }
    val selectedProduct = products.find { it.id == selectedProductId }

    Scaffold(
        containerColor = White,
        bottomBar = {
            Week8BottomBar(
                selectedTab = selectedTab,
                onTabClick = {
                    selectedTab = it
                    selectedProductId = null
                },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(innerPadding),
        ) {
            if (selectedProduct != null) {
                ProductDetailScreen(
                    product = selectedProduct,
                    onBackClick = { selectedProductId = null },
                )
            } else {
                when (selectedTab) {
                    BottomTab.Home -> HomeScreen(
                        products = products,
                        onProductClick = { selectedProductId = it.id },
                    )

                    BottomTab.Shop -> ShopScreen(
                        products = products,
                        onProductClick = { selectedProductId = it.id },
                    )

                    BottomTab.Wishlist -> WishlistScreen(
                        products = products.filter { it.isWishlisted },
                        onProductClick = { selectedProductId = it.id },
                    )

                    BottomTab.Cart -> CartScreen(onOrderClick = { selectedTab = BottomTab.Shop })
                    BottomTab.Profile -> ProfileScreen()
                }
            }
        }
    }
}

@Composable
private fun HomeScreen(
    products: List<ProductUiModel>,
    onProductClick: (ProductUiModel) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .windowInsetsPadding(WindowInsets.statusBars),
    ) {
        Column(modifier = Modifier.padding(horizontal = 34.dp)) {
            Spacer(modifier = Modifier.height(52.dp))
            Text(
                text = "Discover",
                color = Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "9월 4일 목요일",
                color = GrayText,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 6.dp),
            )
            Image(
                painter = painterResource(id = R.drawable.home_logo),
                contentDescription = "홈 화면 배너",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth()
                    .height(330.dp),
            )
            Text(
                text = "추천 상품",
                color = Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 22.dp),
            )
        }

        LazyRow(
            contentPadding = PaddingValues(start = 34.dp, top = 14.dp, end = 34.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                items = products,
                key = { product -> product.id },
            ) { product ->
                ProductHorizontalCard(
                    product = product,
                    onClick = { onProductClick(product) },
                )
            }
        }
    }
}

@Composable
private fun ShopScreen(
    products: List<ProductUiModel>,
    onProductClick: (ProductUiModel) -> Unit,
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
                    onClick = { onProductClick(product) },
                    showHeart = true,
                )
            }
        }
    }
}

@Composable
private fun WishlistScreen(
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
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(26.dp),
        ) {
            items(
                items = products,
                key = { product -> product.id },
            ) { product ->
                ProductGridCard(
                    product = product,
                    onClick = { onProductClick(product) },
                    showHeart = false,
                )
            }
        }
    }
}

@Composable
private fun ProductHorizontalCard(
    product: ProductUiModel,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(164.dp)
            .clickable(onClick = onClick),
    ) {
        ProductImageBox(
            product = product,
            modifier = Modifier
                .fillMaxWidth()
                .height(164.dp),
        )
        ProductTextBlock(product = product, modifier = Modifier.padding(top = 10.dp))
    }
}

@Composable
private fun ProductGridCard(
    product: ProductUiModel,
    onClick: () -> Unit,
    showHeart: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Box {
            ProductImageBox(
                product = product,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(196.dp),
            )
            if (showHeart) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(White.copy(alpha = 0.88f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_heart_straight),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(if (product.isWishlisted) Color.Red else Black),
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
        }
        ProductTextBlock(product = product, modifier = Modifier.padding(top = 12.dp))
    }
}

@Composable
private fun ProductImageBox(
    product: ProductUiModel,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.background(product.backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = null,
            contentScale = if (product.imageRes == R.drawable.home_logo) ContentScale.Crop else ContentScale.Fit,
            colorFilter = if (product.tintImage) ColorFilter.tint(White) else null,
            modifier = Modifier
                .fillMaxSize()
                .padding(if (product.imageRes == R.drawable.home_logo) 0.dp else 52.dp),
        )
    }
}

@Composable
private fun ProductTextBlock(
    product: ProductUiModel,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        if (product.isBestSeller) {
            Text(text = "BestSeller", color = Orange, fontSize = 14.sp)
        }
        Text(
            text = product.name,
            color = Black,
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = if (product.isBestSeller) 6.dp else 0.dp),
        )
        if (product.description.isNotBlank()) {
            Text(
                text = product.description,
                color = GrayText,
                fontSize = 16.sp,
                lineHeight = 19.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
        if (product.colorCount.isNotBlank()) {
            Text(
                text = product.colorCount,
                color = GrayText,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
        Text(
            text = product.price,
            color = Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 6.dp),
        )
    }
}

@Composable
private fun ProductDetailScreen(
    product: ProductUiModel,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 28.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "‹",
                color = Black,
                fontSize = 34.sp,
                modifier = Modifier.clickable(onClick = onBackClick),
            )
            Text(
                text = product.name,
                color = Black,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f),
            )
            Image(
                painter = painterResource(id = R.drawable.ic_heart_straight),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Black),
                modifier = Modifier.size(22.dp),
            )
        }

        ProductImageBox(
            product = product,
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp),
        )
        Text(
            text = product.shortCategory,
            color = Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 24.dp),
        )
        Text(
            text = product.name,
            color = Black,
            fontSize = 26.sp,
            lineHeight = 29.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp),
        )
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(LineGray),
        )
        Text(
            text = product.price,
            color = Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 14.dp),
        )
        Text(
            text = product.detail,
            color = Black,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            modifier = Modifier.padding(top = 26.dp),
        )
        Text(
            text = "• Shown: ${product.colorCount.ifBlank { "Multi-Color" }}\n• Style: SX6897-965",
            color = Black,
            fontSize = 13.sp,
            lineHeight = 19.sp,
            modifier = Modifier.padding(top = 22.dp, start = 8.dp),
        )
        Text(
            text = "View Product Details",
            color = GrayText,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 24.dp),
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            shape = RoundedCornerShape(24.dp),
        ) {
            Text(text = "사이즈 선택  ⌄", color = Black, fontSize = 14.sp)
        }
        Button(
            onClick = {},
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(46.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Black),
            shape = RoundedCornerShape(24.dp),
        ) {
            Text(text = "장바구니에 추가", color = White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .padding(top = 12.dp, bottom = 10.dp)
                .fillMaxWidth()
                .height(46.dp),
            shape = RoundedCornerShape(24.dp),
        ) {
            Text(text = "위시리스트  ♡", color = Black, fontSize = 14.sp)
        }
    }
}

@Composable
private fun CartScreen(onOrderClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 32.dp),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bag_circle),
                contentDescription = null,
                modifier = Modifier.size(48.dp),
            )
            Text(
                text = "장바구니가 비어 있습니다.\n제품을 추가하면 여기에 표시됩니다.",
                color = Black,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 12.dp),
            )
        }

        Button(
            onClick = onOrderClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 28.dp)
                .fillMaxWidth()
                .height(44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Black),
            shape = RoundedCornerShape(24.dp),
        ) {
            Text(
                text = "주문하기",
                color = White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .windowInsetsPadding(WindowInsets.statusBars),
    )
}

@Composable
private fun Week8BottomBar(
    selectedTab: BottomTab,
    onTabClick: (BottomTab) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .height(62.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BottomTab.entries.forEach { tab ->
            val selected = tab == selectedTab
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onTabClick(tab) },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = tab.iconRes),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(if (selected) Black else GrayText),
                    modifier = Modifier.size(22.dp),
                )
                Text(
                    text = tab.label,
                    color = if (selected) Black else GrayText,
                    fontSize = 9.sp,
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.padding(top = 4.dp),
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
        Box(
            modifier = Modifier
                .padding(top = 14.dp)
                .size(width = 82.dp, height = 2.dp)
                .background(if (selected) Black else Color.Transparent),
        )
    }
}

private enum class BottomTab(val label: String, val iconRes: Int) {
    Home("홈", R.drawable.ic_home),
    Shop("구매하기", R.drawable.ic_list_magnifying_glass),
    Wishlist("위시리스트", R.drawable.ic_heart_straight),
    Cart("장바구니", R.drawable.ic_bag_simple),
    Profile("프로필", R.drawable.ic_user),
}

private data class ProductUiModel(
    val id: Int,
    val name: String,
    val description: String,
    val colorCount: String,
    val price: String,
    val shortCategory: String,
    val detail: String,
    val backgroundColor: Color,
    val imageRes: Int,
    val tintImage: Boolean = true,
    val isBestSeller: Boolean = false,
    val isWishlisted: Boolean = false,
)

private val week8Products = listOf(
    ProductUiModel(
        id = 1,
        name = "Nike Everyday Plus Cushioned",
        description = "Training Ankle Socks (6 Pairs)",
        colorCount = "5 Colours",
        price = "US$10",
        shortCategory = "Training Crew Socks",
        detail = "The Nike Everyday Plus Cushioned Socks bring comfort to your workout with extra cushioning under the heel and forefoot and a snug, supportive arch band. Sweat-wicking power and breathability up top help keep your feet dry and cool.",
        backgroundColor = LightGray,
        imageRes = R.drawable.home_logo,
        tintImage = false,
        isWishlisted = true,
    ),
    ProductUiModel(
        id = 2,
        name = "Nike Elite Crew",
        description = "Basketball Socks",
        colorCount = "7 Colours",
        price = "US$16",
        shortCategory = "Basketball Socks",
        detail = "A lightweight everyday sock with soft cushioning and breathable comfort for repeated training sessions.",
        backgroundColor = Color(0xFFE8E8E8),
        imageRes = R.drawable.ic_home,
    ),
    ProductUiModel(
        id = 3,
        name = "Nike Air Force 1 '07",
        description = "Women's Shoes",
        colorCount = "5 Colours",
        price = "US$115",
        shortCategory = "Women's Shoes",
        detail = "Classic hoops style meets crisp leather, bold details and just the right amount of shine.",
        backgroundColor = Color(0xFFB8B8B8),
        imageRes = R.drawable.ic_bag_simple,
        isBestSeller = true,
    ),
    ProductUiModel(
        id = 4,
        name = "Jordan ENike Air Force 1 '07essentials",
        description = "Men's Shoes",
        colorCount = "2 Colours",
        price = "US$115",
        shortCategory = "Men's Shoes",
        detail = "A clean everyday sneaker inspired by court style and built for all-day comfort.",
        backgroundColor = Color(0xFF444444),
        imageRes = R.drawable.ic_user,
        isBestSeller = true,
    ),
    ProductUiModel(
        id = 5,
        name = "Air Jordan 1 Mid",
        description = "",
        colorCount = "",
        price = "US$125",
        shortCategory = "Men's Shoes",
        detail = "Premium materials and classic Jordan details deliver a timeless mid-top look.",
        backgroundColor = Color(0xFFC9C9C9),
        imageRes = R.drawable.home_logo,
        tintImage = false,
        isWishlisted = true,
    ),
)
