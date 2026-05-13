package com.example.week2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week2.ui.theme.NikeBlack
import com.example.week2.ui.theme.NikeGray

@Composable
fun ProductItem(
    product: Product,
    onItemClick: (Product) -> Unit,
    onWishlistClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .clickable { onItemClick(product) }
    ) {
        Box {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { onWishlistClick(product) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = if (product.isWishlisted) R.drawable.ic_heart_fill else R.drawable.ic_heart_empty
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = NikeBlack,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = product.description,
            fontSize = 12.sp,
            color = NikeGray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = product.price,
            fontSize = 14.sp,
            color = NikeBlack,
            fontWeight = FontWeight.Medium
        )
    }
}
