package com.example.week2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week2.ui.theme.NikeBlack

@Composable
fun CartScreen(onOrderClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.ic_bagcircle),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "장바구니가 비어 있습니다.\n제품을 추가하면 여기에 표시됩니다.",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = NikeBlack
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onOrderClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = NikeBlack),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(
                text = "주문하기",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
