package com.example.week9.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week9.R
import com.example.week9.model.ProductUiModel
import com.example.week9.ui.component.ProductImageBox

private val White = Color.White
private val Black = Color(0xFF111111)
private val GrayText = Color(0xFF767676)
private val LineGray = Color(0xFFD7D7D7)

@Composable
fun ProductDetailScreen(
    product: ProductUiModel,
    isWishlisted: Boolean,
    onBackClick: () -> Unit,
    onWishClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 28.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "‹",
                color = Black,
                fontSize = 34.sp,
                modifier = Modifier.clickable(onClick = onBackClick),
            )
            Text(
                text = product.name,
                color = Black,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f),
            )
            Image(
                painter = painterResource(
                    id = if (isWishlisted) R.drawable.ic_heart_filled else R.drawable.ic_heart_straight,
                ),
                contentDescription = null,
                colorFilter = if (isWishlisted) null else ColorFilter.tint(Black),
                modifier = Modifier
                    .size(22.dp)
                    .clickable(onClick = onWishClick),
            )
        }

        ProductImageBox(
            product = product,
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp),
        )
        Text(
            text = product.shortCategory,
            color = Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 24.dp),
        )
        Text(
            text = product.name,
            color = Black,
            fontSize = 26.sp,
            lineHeight = 29.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp),
        )
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(LineGray),
        )
        Text(
            text = product.price,
            color = Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 14.dp),
        )
        Text(
            text = product.detail,
            color = Black,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            modifier = Modifier.padding(top = 26.dp),
        )
        Text(
            text = "• Shown: ${product.colorCount.ifBlank { "Multi-Color" }}\n• Style: SX6897-965",
            color = Black,
            fontSize = 13.sp,
            lineHeight = 19.sp,
            modifier = Modifier.padding(top = 22.dp, start = 8.dp),
        )
        Text(
            text = "View Product Details",
            color = GrayText,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 24.dp),
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            shape = RoundedCornerShape(24.dp),
        ) {
            Text(text = "사이즈 선택  ⌄", color = Black, fontSize = 14.sp)
        }
        Button(
            onClick = {},
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(46.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Black),
            shape = RoundedCornerShape(24.dp),
        ) {
            Text(text = "장바구니에 추가", color = White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        OutlinedButton(
            onClick = onWishClick,
            modifier = Modifier
                .padding(top = 12.dp, bottom = 10.dp)
                .fillMaxWidth()
                .height(46.dp),
            shape = RoundedCornerShape(24.dp),
        ) {
            Text(text = if (isWishlisted) "위시리스트 해제  ♥" else "위시리스트  ♡", color = Black, fontSize = 14.sp)
        }
    }
}





