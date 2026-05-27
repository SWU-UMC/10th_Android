package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import com.example.myapplication.model.Product

@Composable
fun StoreScreen(
    wishlistedIds: List<Int>,
    onHeartToggle: (Int) -> Unit
) {
    var selectedCategory by remember { mutableStateOf("전체") }

    val categories = listOf(
        "전체",
        "Tops & Shirts",
        "Sale"
    )

    val filteredProducts =
        if (selectedCategory == "전체") {
            sampleStoreProducts
        } else {
            sampleStoreProducts.filter {
                it.category == selectedCategory
            }
        }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {


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
                        .clickable {
                            selectedCategory = category
                        }
                        .padding(8.dp),
                    fontWeight =
                        if (selectedCategory == category)
                            FontWeight.Bold
                        else
                            FontWeight.Normal,
                    color =
                        if (selectedCategory == category)
                            Color.Black
                        else
                            Color.Gray
                )
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(12.dp))


        val rows = filteredProducts.chunked(2)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            items(rows) { rowItems ->

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    rowItems.forEach { product ->

                        StoreProductItem(
                            product = product,
                            isLiked = wishlistedIds.contains(product.id),
                            onHeartClick = {
                                onHeartToggle(product.id)
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    // 한 줄에 하나만 있을 때 빈 공간 맞추기
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun StoreProductItem(
    product: Product,
    isLiked: Boolean,
    onHeartClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {

            Card(
                shape = RoundedCornerShape(16.dp),
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
            ) {

                Icon(
                    imageVector =
                        if (isLiked)
                            Icons.Default.Favorite
                        else
                            Icons.Default.FavoriteBorder,
                    contentDescription = "Heart",
                    tint =
                        if (isLiked)
                            Color.Red
                        else
                            Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.name,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Text(
            text = product.price,
            color = Color.Gray,
            fontSize = 13.sp
        )
    }
}