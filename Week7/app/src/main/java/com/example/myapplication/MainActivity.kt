package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.StoreProduct

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf("홈") }

    val wishlistedIds = remember { mutableStateListOf<Int>() }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val items = listOf("홈", "구매하기", "위시리스트", "장바구니", "프로필")
                val icons = listOf(
                    Icons.Default.Home,
                    Icons.Default.Store,
                    Icons.Default.Favorite,
                    Icons.Default.ShoppingCart,
                    Icons.Default.Person
                )

                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedTab == item,
                        onClick = { selectedTab = item },
                        icon = { Icon(imageVector = icons[index], contentDescription = item) },
                        label = { Text(item) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                "홈" -> HomeScreen()

                "구매하기" -> StoreScreen(
                    wishlistedIds = wishlistedIds,
                    onHeartToggle = { id ->
                        if (wishlistedIds.contains(id)) {
                            wishlistedIds.remove(id)
                        } else {
                            wishlistedIds.add(id)
                        }
                    }
                )

                "위시리스트" -> WishlistScreen(
                    wishlistedIds = wishlistedIds,
                    onHeartToggle = { id ->
                        if (wishlistedIds.contains(id)) {
                            wishlistedIds.remove(id)
                        }
                    }
                )

                "장바구니" -> CartScreen(onOrderClick = { selectedTab = "구매하기" })

                else -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("${selectedTab} 화면")
                    }
                }
            }
        }
    }
}

private val sampleStoreProducts = listOf(
    StoreProduct(1, "Adidas", R.drawable.adidas, "$89.00", "Tops & Shirts"),
    StoreProduct(2, "Nike", R.drawable.nike, "$99.00", "Tops & Shirts"),
    StoreProduct(3, "Lecoqsportif", R.drawable.lecoqsportif, "$120.00", "Sale"),
    StoreProduct(4, "Fila", R.drawable.fila, "$59.00", "Sale")
)

@Composable
fun StoreScreen(
    wishlistedIds: List<Int>,
    onHeartToggle: (Int) -> Unit
) {
    var selectedCategory by remember { mutableStateOf("전체") }
    val categories = listOf("전체", "Tops & Shirts", "Sale")

    val filteredProducts = remember(selectedCategory) {
        if (selectedCategory == "전체") {
            sampleStoreProducts
        } else {
            sampleStoreProducts.filter { it.category == selectedCategory }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            categories.forEach { category ->
                Text(
                    text = category,
                    modifier = Modifier
                        .clickable { selectedCategory = category }
                        .padding(8.dp),
                    fontWeight = if (selectedCategory == category) FontWeight.Bold else FontWeight.Normal,
                    color = if (selectedCategory == category) Color.Black else Color.Gray
                )
            }
        }
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        if (filteredProducts.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "상품이 없습니다.")
            }
        } else {
            val chunkedProducts = filteredProducts.chunked(2)

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // [요구사항] items() 사용 및 고유 key 지정하여 상태 안정성 확보
                items(
                    items = chunkedProducts,
                    key = { row -> row.map { it.id }.joinToString("-") }
                ) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        for (product in rowItems) {
                            Box(modifier = Modifier.weight(1f)) {

                                StoreProductItem(
                                    product = product,
                                    isLiked = wishlistedIds.contains(product.id),
                                    onHeartClick = { onHeartToggle(product.id) }
                                )
                            }
                        }

                        if (rowItems.size < 2) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StoreProductItem(
    product: StoreProduct,
    isLiked: Boolean,
    onHeartClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }


            IconButton(
                onClick = onHeartClick,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Wishlist Heart",
                    tint = if (isLiked) Color.Red else Color.LightGray,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = product.name,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(2.dp))


        Text(
            text = product.price,
            fontSize = 13.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun WishlistScreen(
    wishlistedIds: List<Int>,
    onHeartToggle: (Int) -> Unit
) {

    val wishlistedProducts = sampleStoreProducts.filter { wishlistedIds.contains(it.id) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "My Wishlist",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        if (wishlistedProducts.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Empty Wishlist",
                        tint = Color.Gray,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "위시리스트가 비어있습니다.",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "구매하기에서 하트를 눌러보세요!",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        } else {

            val chunkedWishlist = wishlistedProducts.chunked(2)

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(
                    items = chunkedWishlist,
                    key = { row -> row.map { it.id }.joinToString("-") }
                ) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        for (product in rowItems) {
                            Box(modifier = Modifier.weight(1f)) {
                                StoreProductItem(
                                    product = product,
                                    isLiked = true,
                                    onHeartClick = { onHeartToggle(product.id) }
                                )
                            }
                        }
                        if (rowItems.size < 2) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CartScreen(onOrderClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_cartcircle),
            contentDescription = "장바구니 아이콘",
            modifier = Modifier.size(100.dp),
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "장바구니가 비어있습니다.", fontWeight = FontWeight.Bold)
        Text(text = "제품을 추가하면 여기에 표시됩니다.", color = Color.Gray)

        Spacer(modifier = Modifier.weight(1f))

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable { onOrderClick() },
            shape = RoundedCornerShape(28.dp),
            color = Color.Black
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "주문하기", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}