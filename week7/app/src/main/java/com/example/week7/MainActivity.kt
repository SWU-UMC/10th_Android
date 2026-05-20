package com.example.week7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week7.ui.theme.Week7Theme

private val White = Color.White
private val Black = Color(0xFF111111)
private val GrayText = Color(0xFF767676)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week7Theme {
                Week7App()
            }
        }
    }
}

@Composable
private fun Week7App() {
    var selectedTab by rememberSaveable { mutableStateOf(BottomTab.Home) }

    Scaffold(
        containerColor = White,
        bottomBar = {
            Week7BottomBar(
                selectedTab = selectedTab,
                onTabClick = { selectedTab = it },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(innerPadding),
        ) {
            when (selectedTab) {
                BottomTab.Home -> HomeScreen()
                BottomTab.Shop -> ShopScreen()
                BottomTab.Wishlist -> WishlistScreen()
                BottomTab.Cart -> CartScreen(onOrderClick = { selectedTab = BottomTab.Shop })
                BottomTab.Profile -> ProfileScreen()
            }
        }
    }
}

@Composable
private fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 34.dp),
    ) {
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
                .height(402.dp),
        )
    }
}

@Composable
private fun ShopScreen() {
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
            ShopTab(text = "Shoes", selected = false)
        }
    }
}

@Composable
private fun WishlistScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(start = 20.dp, top = 58.dp),
    ) {
        Text(
            text = "위시리스트",
            color = Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
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
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
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
private fun Week7BottomBar(
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
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
        )
        Box(
            modifier = Modifier
                .padding(top = 14.dp)
                .size(width = 64.dp, height = 2.dp)
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
