package com.example.week7.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week7.R

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Discover", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = "10월 31일 화요일", color = Color.Gray, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_homeimg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ShopScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "전체", fontWeight = FontWeight.Bold)
                Box(modifier = Modifier.width(40.dp).height(2.dp).background(Color.Black))
            }
            Text(text = "TOPS & T-SHIRTS", color = Color.Gray)
            Text(text = "SHOES", color = Color.Gray)
        }
    }
}

@Composable
fun WishlistScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "위시리스트", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CartScreen(onOrderClick: () -> Unit) { // 콜백 매개변수 추가
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bagcircle),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "장바구니가 비어 있습니다.", fontWeight = FontWeight.Bold)
            Text(text = "제품을 추가하면 여기에 표시됩니다.", color = Color.Gray, fontSize = 12.sp)
        }
        
        Button(
            onClick = onOrderClick, // 버튼 클릭 시 콜백 호출
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .fillMaxWidth(0.8f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(text = "주문하기", color = Color.White)
        }
    }
}

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Profile Fragment", color = Color.Gray)
    }
}
