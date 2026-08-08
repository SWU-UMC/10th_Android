package com.example.week2

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    viewModel: ProductViewModel,
    onNavigateToDetail: (Int) -> Unit
) {
    val products = viewModel.homeProducts

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(start = 20.dp, top = 20.dp)) {
                Text(
                    text = stringResource(id = R.string.home_discover),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.nike_black)
                )
                Text(
                    text = stringResource(id = R.string.home_date),
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.nike_gray)
                )
            }
        }

        item {
            Image(
                painter = painterResource(id = R.drawable.ic_homeimg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                contentScale = ContentScale.FillWidth
            )
        }

        item {
            Column(modifier = Modifier.padding(start = 20.dp, top = 20.dp)) {
                Text(
                    text = "What's new",
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.nike_gray)
                )
                Text(
                    text = "나이키 최신 상품",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.nike_black)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val itemWidth = maxWidth
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    products.forEach { product ->
                        ProductItem(
                            product = product,
                            onItemClick = { onNavigateToDetail(it.id) },
                            onWishlistClick = { viewModel.toggleWishlist(it.id) },
                            modifier = Modifier.width(itemWidth)
                        )
                    }
                }
            }
        }
    }
}
