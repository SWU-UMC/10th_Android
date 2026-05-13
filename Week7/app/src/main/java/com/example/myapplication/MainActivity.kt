package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

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
                "구매하기" -> StoreScreen()
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
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.home_logo),
            contentDescription = "홈 로고",
            modifier = Modifier.size(200.dp),
            tint = Color.Unspecified
        )
    }
}


@Composable
fun StoreScreen() {
    var selectedCategory by remember { mutableStateOf("전체") }
    val categories = listOf("전체", "Tops & Shirts", "Sale")

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

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "$selectedCategory 리스트가 여기에 표시됩니다.")
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