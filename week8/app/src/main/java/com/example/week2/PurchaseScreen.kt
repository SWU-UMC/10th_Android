package com.example.week2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun PurchaseScreen(
    viewModel: ProductViewModel,
    onNavigateToDetail: (Int) -> Unit
) {
    val tabs = listOf(
        stringResource(id = R.string.purchase_all),
        stringResource(id = R.string.purchase_tops_tshirts),
        stringResource(id = R.string.purchase_shoes)
    )
    var selectedTab by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            edgePadding = 0.dp,
            containerColor = androidx.compose.ui.graphics.Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = colorResource(id = R.color.nike_black)
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTab == index)
                                colorResource(id = R.color.nike_black)
                            else
                                colorResource(id = R.color.nike_gray)
                        )
                    }
                )
            }
        }

        ProductGrid(
            products = viewModel.purchaseProducts,
            onItemClick = { product -> onNavigateToDetail(product.id) },
            onWishlistClick = { product -> viewModel.toggleWishlist(product.id) }
        )
    }
}
