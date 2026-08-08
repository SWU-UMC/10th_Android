package com.example.week2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.week2.data.UserData

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel = viewModel()) {
    val uiState by profileViewModel.uiState.collectAsState()

    when (val state = uiState) {
        is ProfileViewModel.UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = colorResource(id = R.color.nike_black))
            }
        }
        is ProfileViewModel.UiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.message, color = Color.Red)
            }
        }
        is ProfileViewModel.UiState.Success -> {
            ProfileContent(user = state.user, following = state.following)
        }
    }
}

@Composable
private fun ProfileContent(user: UserData, following: List<UserData>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        ProfileHeader(user = user)
        HorizontalDivider(color = colorResource(id = R.color.border_gray))
        QuickMenuSection()
        HorizontalDivider(color = colorResource(id = R.color.border_gray))
        NikeMemberBenefitsRow()
        HorizontalDivider(color = colorResource(id = R.color.border_gray))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(colorResource(id = R.color.background_light))
        )
        FollowingSection(following = following)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(colorResource(id = R.color.background_light))
        )
        JoinDateFooter()
    }
}

@Composable
private fun ProfileHeader(user: UserData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = user.avatar,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "${user.firstName} ${user.lastName}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.nike_black)
        )
        Spacer(modifier = Modifier.height(14.dp))
        OutlinedButton(
            onClick = {},
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, colorResource(id = R.color.border_gray)),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = colorResource(id = R.color.nike_black)
            ),
            contentPadding = PaddingValues(horizontal = 36.dp, vertical = 10.dp)
        ) {
            Text(text = "프로필 수정", fontSize = 14.sp)
        }
    }
}

private data class QuickMenuItem(val iconRes: Int, val label: String)

@Composable
private fun QuickMenuSection() {
    val items = listOf(
        QuickMenuItem(R.drawable.ic_order, "주문"),
        QuickMenuItem(R.drawable.ic_identificationcard, "패스"),
        QuickMenuItem(R.drawable.ic_event, "이벤트"),
        QuickMenuItem(R.drawable.ic_setting, "설정")
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(vertical = 20.dp)
    ) {
        items.forEachIndexed { index, item ->
            if (index > 0) {
                VerticalDivider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 4.dp),
                    color = colorResource(id = R.color.border_gray)
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = item.iconRes),
                    contentDescription = item.label,
                    modifier = Modifier.size(26.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.label,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.nike_black)
                )
            }
        }
    }
}

@Composable
private fun NikeMemberBenefitsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "나이키 멤버 혜택",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.nike_black)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "0개 사용 가능",
                fontSize = 13.sp,
                color = colorResource(id = R.color.nike_gray)
            )
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = colorResource(id = R.color.nike_gray)
        )
    }
}

@Composable
private fun FollowingSection(following: List<UserData>) {
    if (following.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { following.size })

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 16.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "팔로잉 (${following.size})",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.nike_black)
        )
        Text(
            text = "편집",
            fontSize = 14.sp,
            color = colorResource(id = R.color.nike_gray)
        )
    }

    HorizontalPager(
        state = pagerState,
        pageSize = PageSize.Fixed(90.dp),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp),
        pageSpacing = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
    ) { page ->
        FollowingAvatar(user = following[page])
    }

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun FollowingAvatar(user: UserData) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = user.avatar,
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = user.firstName,
            fontSize = 12.sp,
            color = colorResource(id = R.color.nike_black),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun JoinDateFooter() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.background_light))
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "회원 가입일: 2025년 9월",
            fontSize = 13.sp,
            color = colorResource(id = R.color.nike_gray)
        )
    }
}
