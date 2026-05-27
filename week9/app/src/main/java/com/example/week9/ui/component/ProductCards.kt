package com.example.week9.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week9.R
import com.example.week9.model.ProductUiModel

private val Black = Color(0xFF111111)
private val GrayText = Color(0xFF767676)
private val White = Color.White
private val Orange = Color(0xFFE0642A)

@Composable
fun ProductHorizontalCard(
    product: ProductUiModel,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(314.dp)
            .clickable(onClick = onClick),
    ) {
        ProductImageBox(
            product = product,
            modifier = Modifier
                .fillMaxWidth()
                .height(314.dp),
        )
        ProductTextBlock(product = product, modifier = Modifier.padding(top = 10.dp))
    }
}

@Composable
fun ProductGridCard(
    product: ProductUiModel,
    isWishlisted: Boolean,
    onClick: () -> Unit,
    onWishClick: () -> Unit,
    showHeart: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Box {
            ProductImageBox(
                product = product,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(196.dp),
            )
            if (showHeart) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(White.copy(alpha = 0.88f))
                        .clickable(onClick = onWishClick),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(
                            id = if (isWishlisted) R.drawable.ic_heart_filled else R.drawable.ic_heart_straight,
                        ),
                        contentDescription = null,
                        colorFilter = if (isWishlisted) null else ColorFilter.tint(Black),
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
        }
        ProductTextBlock(product = product, modifier = Modifier.padding(top = 12.dp))
    }
}

@Composable
fun ProductImageBox(
    product: ProductUiModel,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.background(product.backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = null,
            contentScale = if (product.imageRes == R.drawable.home_logo) ContentScale.Crop else ContentScale.Fit,
            colorFilter = if (product.tintImage) ColorFilter.tint(White) else null,
            modifier = Modifier
                .fillMaxSize()
                .padding(if (product.imageRes == R.drawable.home_logo) 0.dp else 52.dp),
        )
    }
}

@Composable
fun ProductTextBlock(
    product: ProductUiModel,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        if (product.isBestSeller) {
            Text(text = "BestSeller", color = Orange, fontSize = 14.sp)
        }
        Text(
            text = product.name,
            color = Black,
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = if (product.isBestSeller) 6.dp else 0.dp),
        )
        if (product.description.isNotBlank()) {
            Text(
                text = product.description,
                color = GrayText,
                fontSize = 16.sp,
                lineHeight = 19.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
        if (product.colorCount.isNotBlank()) {
            Text(
                text = product.colorCount,
                color = GrayText,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
        Text(
            text = product.price,
            color = Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 6.dp),
        )
    }
}






