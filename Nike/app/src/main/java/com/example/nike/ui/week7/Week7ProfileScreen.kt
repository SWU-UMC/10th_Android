package com.example.nike.ui.week7

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nike.R

@Composable
fun Week7ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Week7Colors.Background)
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(top = 52.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_avatar_placeholder),
                contentDescription = "프로필 이미지",
                modifier = Modifier
                    .size(86.dp)
                    .clip(CircleShape),
            )
            Text(
                text = "George Bluth",
                color = Week7Colors.TextPrimary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 24.dp),
            )
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .padding(top = 26.dp)
                    .width(180.dp)
                    .height(52.dp),
                border = BorderStroke(1.dp, Week7Colors.Divider),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Week7Colors.TextPrimary,
                ),
                shape = RoundedCornerShape(26.dp),
            ) {
                Text(text = "프로필 수정", fontSize = 14.sp)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(top = 28.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ProfileMetric(title = "주문", modifier = Modifier.weight(1f))
                ProfileDivider()
                ProfileMetric(title = "패스", modifier = Modifier.weight(1f))
                ProfileDivider()
                ProfileMetric(title = "이벤트", modifier = Modifier.weight(1f))
                ProfileDivider()
                ProfileMetric(title = "설정", modifier = Modifier.weight(1f))
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(Week7Colors.Background),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp)
                .background(Color.White)
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "나이키 멤버 혜택",
                    color = Week7Colors.TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "0개 사용 가능",
                    color = Week7Colors.TextMuted,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
            Text(text = "›", color = Week7Colors.TextPrimary, fontSize = 32.sp)
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(Week7Colors.Background),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 24.dp, top = 26.dp, end = 24.dp, bottom = 24.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "팔로잉 (5)",
                    color = Week7Colors.TextPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                )
                Text(text = "편집", color = Week7Colors.TextSecondary, fontSize = 13.sp)
            }
            Text(
                text = "팔로잉 목록은 기존 서버 연동 화면에서 확인할 수 있습니다.",
                color = Week7Colors.TextMuted,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp),
            )
        }

        Text(
            text = "회원 가입일: 2025년 9월",
            color = Week7Colors.TextMuted,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp)
                .background(Color.White)
                .padding(top = 16.dp),
        )
    }
}

@Composable
private fun ProfileMetric(
    title: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = title, color = Week7Colors.TextSecondary, fontSize = 12.sp)
    }
}

@Composable
private fun ProfileDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(34.dp)
            .background(Week7Colors.Divider),
    )
}
