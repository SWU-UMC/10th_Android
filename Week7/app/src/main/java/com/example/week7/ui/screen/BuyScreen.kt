package com.example.week7.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.week7.R
import com.example.week7.ui.components.ProductData
import com.example.week7.ui.components.ProductGridItem
import com.example.week7.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BuyScreen(
    viewModel: MainViewModel
) {
    val tabs = listOf(
        stringResource(R.string.all),
        stringResource(R.string.Shirts),
        stringResource(R.string.Shoes)
    )

    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = pagerState.targetPage,
            containerColor = Color.White,
            contentColor = Color.Black,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.targetPage]),
                    height = 3.dp,
                    color = Color.Black
                )
            },
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = pagerState.targetPage == index
                Tab(
                    selected = isSelected,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) Color.Black else Color.Gray
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> BuyAllScreen(
                    products = viewModel.productList,
                    onWishToggle = { id -> viewModel.toggleWishStatus(id) }
                )
                1 -> BuyTopsScreen()
                2 -> BuyShoesScreen()
            }
        }
    }
}

@Composable
fun BuyAllScreen(
    products: List<ProductData>,
    onWishToggle: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = products,
            key = { product -> product.id },
            contentType = { "product_item" }
        ) { product ->
            ProductGridItem(
                product = product,
                showWishButton = true,
                onWishClick = { onWishToggle(product.id) }
            )
        }
    }
}

@Composable
fun BuyTopsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = stringResource(id = R.string.tap2))
    }
}

@Composable
fun BuyShoesScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = stringResource(id = R.string.tap3))
    }
}