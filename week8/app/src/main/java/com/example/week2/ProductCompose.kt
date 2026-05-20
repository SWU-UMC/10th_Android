package com.example.week2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductItem(
    product: Product,
    onItemClick: (Product) -> Unit,
    onWishlistClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(product) }
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            IconButton(
                onClick = { onWishlistClick(product) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(48.dp) // Larger touch target
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = if (product.isWishlisted) R.drawable.ic_heart_fill else R.drawable.ic_heart_empty
                    ),
                    contentDescription = "Wishlist",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.name,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_main)
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = product.description,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.sp,
                color = colorResource(id = R.color.text_secondary)
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = product.price,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_main)
            )
        )
    }
}

@Composable
fun ProductList(
    products: List<Product>,
    onItemClick: (Product) -> Unit,
    onWishlistClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(
            items = products,
            key = { product -> product.id }
        ) { product ->
            ProductItem(
                product = product,
                onItemClick = onItemClick,
                onWishlistClick = onWishlistClick
            )
        }
    }
}
