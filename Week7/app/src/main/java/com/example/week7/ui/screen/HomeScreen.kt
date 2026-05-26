package com.example.week7.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week7.R
import com.example.week7.ui.components.ProductData
import com.example.week7.viewmodel.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val products = viewModel.productList

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {

        item {
            Text(
                text = stringResource(id = R.string.Title),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 24.dp, start = 20.dp)
            )
        }

        item {
            Text(
                text = stringResource(id = R.string.date),
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 20.dp, bottom = 30.dp)
            )
        }

        item {
            Image(
                painter = painterResource(id = R.drawable.img_home),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        item {
            Text(
                text = stringResource(id = R.string.newEn),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp)
            )
        }

        item {
            Text(
                text = stringResource(id = R.string.newKr),
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 20.dp, bottom = 12.dp)
            )
        }

        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = products,
                    key = { item -> item.id },
                    contentType = { "ProductHorizontalItem" }
                ) { product ->
                    HorizontalProductItem(
                        product = product,
                        onWishClick = { viewModel.toggleWishStatus(product.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun HorizontalProductItem(
    product: ProductData,
    onWishClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(140.dp)
            .padding(4.dp)
    ) {
        Box(modifier = Modifier.size(140.dp)) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Icon(
                painter = painterResource(
                    id = if (product.isWished) R.drawable.ic_wish_heart_fill else R.drawable.ic_wish_heart
                ),
                contentDescription = "Wishlist Button",
                tint = if (product.isWished) Color.Red else Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(24.dp)
                    .clickable { onWishClick() }
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(text = product.name, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Text(text = product.desc, fontSize = 12.sp, color = Color.Gray)
        Text(text = "색상 ${product.colorCount}개", fontSize = 11.sp, color = Color.DarkGray)
        Text(text = product.price, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
    }
}