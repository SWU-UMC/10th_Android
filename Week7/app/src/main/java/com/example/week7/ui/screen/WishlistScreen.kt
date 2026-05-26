package com.example.week7.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week7.R
import com.example.week7.ui.components.ProductGridItem
import com.example.week7.viewmodel.MainViewModel
import androidx.compose.runtime.remember

@Composable
fun WishlistScreen(
    viewModel: MainViewModel
) {
    val favoriteProducts = remember(viewModel.productList) {
        viewModel.productList.filter { it.isWished }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.WishList),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(
                items = favoriteProducts,
                key = { product -> product.id },
                contentType = { "wish_product_item" }
            ) { product ->
                ProductGridItem(
                    product = product,
                    showWishButton = true,
                    onWishClick = { viewModel.toggleWishStatus(product.id) }
                )
            }
        }
    }
}