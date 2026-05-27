package com.example.week9.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week9.R
import com.example.week9.model.ProductUiModel
import com.example.week9.ui.component.ProductHorizontalCard

private val White = Color.White
private val Black = Color(0xFF111111)
private val GrayText = Color(0xFF767676)

@Composable
fun HomeScreen(
    products: List<ProductUiModel>,
    onProductClick: (ProductUiModel) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .windowInsetsPadding(WindowInsets.statusBars),
        contentPadding = PaddingValues(bottom = 24.dp),
    ) {
        item(contentType = "header") {
            Column {
                Spacer(modifier = Modifier.height(69.dp))
                Text(
                    text = "Discover",
                    color = Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 34.dp),
                )
                Text(
                    text = "9월 4일 목요일",
                    color = GrayText,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 34.dp, top = 6.dp),
                )
                Image(
                    painter = painterResource(id = R.drawable.home_logo),
                    contentDescription = "홈 화면 배너",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 17.dp, top = 26.dp)
                        .width(378.dp)
                        .height(505.dp),
                )
            }
        }

        item(contentType = "sectionTitle") {
            Column(modifier = Modifier.padding(start = 34.dp, top = 26.dp, end = 34.dp)) {
                Text(
                    text = "What’s new",
                    color = Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "나이키 최신 상품",
                    color = GrayText,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 6.dp),
                )
            }
        }

        item(contentType = "productRow") {
            LazyRow(
                contentPadding = PaddingValues(start = 34.dp, top = 18.dp, end = 34.dp, bottom = 34.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
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
}


