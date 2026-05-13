package com.example.nike.ui.week7

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.nike.R
import com.example.nike.data.model.Product

@Composable
fun Week7ProductCard(
    product: Product,
    onHeartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.9f)
                .background(Week7Colors.Divider),
        ) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            IconButton(
                onClick = onHeartClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
                    .size(36.dp),
            ) {
                Image(
                    painter = painterResource(id = if (product.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heartstraight),
                    contentDescription = if (product.isLiked) "위시리스트 해제" else "위시리스트 등록",
                    modifier = Modifier.size(22.dp),
                )
            }
        }

        if (product.isBestSeller) {
            Text(
                text = "BestSeller",
                color = Week7Colors.Orange,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 8.dp),
            )
        }

        Text(
            text = product.name,
            color = Week7Colors.TextPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 6.dp),
        )
        Text(
            text = product.description.ifBlank { "Nike Sportswear" },
            color = Week7Colors.TextSecondary,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 2.dp),
        )
        Text(
            text = "${if (product.colorCount == 0) 5 else product.colorCount} Colours",
            color = Week7Colors.TextSecondary,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 2.dp),
        )
        Text(
            text = "₩%,d".format(product.price),
            color = Week7Colors.TextPrimary,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 6.dp),
        )
    }
}