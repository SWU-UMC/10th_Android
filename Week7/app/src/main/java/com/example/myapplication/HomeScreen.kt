package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun HomeScreen() {
    val jordanList = listOf(
        JordanProduct(1, "AirJordan_1", R.drawable.jordan_1, "₩219,000"),
        JordanProduct(2, "Airjordan_2", R.drawable.jordan_2, "₩199,000"),
        JordanProduct(3, "AirJordan_3", R.drawable.jordan_3, "₩209,000")
    )

    val recommendedList = listOf(
        JordanProduct(4, "Air Force 1 '07", R.drawable.home_logo, "₩139,000", "클래식한 멋의 정석"),
        JordanProduct(5, "Dunk Low Retro", R.drawable.home_logo, "₩129,000", "스트릿 패션 필수 아이템"),
        JordanProduct(6, "Air Max 97", R.drawable.home_logo, "₩219,000", "시대를 앞서가는 에어 시스템")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {

        item {
            Image(
                painter = painterResource(id = R.drawable.home_logo),
                contentDescription = "홈 브랜드 로고",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.FillWidth
            )
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Text(
                    text = "What's new",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "나이키 최신 상품",
                    color = Color.Gray,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = jordanList,
                    key = { it.id }
                ) { jordan ->
                    JordanHorizontalItem(product = jordan)
                }
            }
        }
    }
}

@Composable
fun JordanHorizontalItem(product: JordanProduct) {
    Column(
        modifier = Modifier.width(180.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = Color.Black
        )
        Text(
            text = product.price,
            fontSize = 13.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Medium
        )
    }
}
