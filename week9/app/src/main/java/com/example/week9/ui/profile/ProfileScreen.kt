package com.example.week9.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.week9.R
import com.example.week9.data.followingUsers
import com.example.week9.model.FollowingUiModel
import com.example.week9.model.UserProfileUiModel
import com.example.week9.network.ReqResUserClient

private val Black = Color(0xFF111111)
private val GrayText = Color(0xFF8A8A8A)
private val LightGray = Color(0xFFF5F5F5)
private val BorderGray = Color(0xFFE7E7E7)
private val White = Color.White

@Composable
fun ProfileScreen() {
    var reloadKey by remember { mutableIntStateOf(0) }
    var uiState by remember { mutableStateOf<ProfileUiState>(ProfileUiState.Loading) }

    LaunchedEffect(reloadKey) {
        uiState = ProfileUiState.Loading
        uiState = runCatching { ReqResUserClient.fetchUser(userId = 1) }
            .fold(
                onSuccess = { ProfileUiState.Success(it) },
                onFailure = { ProfileUiState.Error },
            )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .windowInsetsPadding(WindowInsets.statusBars),
    ) {
        when (val state = uiState) {
            ProfileUiState.Loading -> LoadingProfile()
            ProfileUiState.Error -> ErrorProfile(onRetryClick = { reloadKey++ })
            is ProfileUiState.Success -> ProfileContent(profile = state.profile)
        }
    }
}

@Composable
private fun ProfileContent(profile: UserProfileUiModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
    ) {
        Spacer(modifier = Modifier.height(36.dp))
        ProfileHeader(profile = profile)
        Spacer(modifier = Modifier.height(30.dp))
        ProfileShortcutRow()
        Spacer(modifier = Modifier.height(12.dp))
        BenefitRow()
        Spacer(modifier = Modifier.height(12.dp))
        FollowingPager(followings = followingUsers)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "회원 가입일: 2025년 9월",
            color = GrayText,
            fontSize = 11.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun ProfileHeader(profile: UserProfileUiModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = profile.avatarUrl,
            contentDescription = "${profile.displayName} 프로필 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(92.dp)
                .clip(CircleShape),
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = profile.displayName,
            color = Black,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = profile.email,
            color = GrayText,
            fontSize = 12.sp,
        )
        Spacer(modifier = Modifier.height(18.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Black, contentColor = White),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .width(190.dp)
                .height(48.dp),
        ) {
            Text(text = "프로필 수정", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun ProfileShortcutRow() {
    val items = listOf(
        ProfileShortcut("주문", R.drawable.ic_profile_order),
        ProfileShortcut("패스", R.drawable.ic_profile_pass),
        ProfileShortcut("이벤트", R.drawable.ic_profile_event),
        ProfileShortcut("설정", R.drawable.ic_profile_setting),
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEachIndexed { index, item ->
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = item.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.label,
                    color = GrayText,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                )
            }
            if (index != items.lastIndex) {
                Box(
                    modifier = Modifier
                        .height(36.dp)
                        .width(1.dp)
                        .background(BorderGray),
                )
            }
        }
    }
}

private data class ProfileShortcut(
    val label: String,
    val iconRes: Int,
)

@Composable
private fun BenefitRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightGray)
            .padding(vertical = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(horizontal = 22.dp, vertical = 22.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "나이키 멤버 혜택", color = Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "0개 사용 가능", color = GrayText, fontSize = 13.sp)
            }
            Text(text = ">", color = Black, fontSize = 26.sp)
        }
    }
}

@Composable
private fun FollowingPager(followings: List<FollowingUiModel>) {
    val pages = remember(followings) { followings.chunked(3) }
    val pagerState = rememberPagerState(pageCount = { pages.size })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(top = 22.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Following (${followings.size})",
                color = Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
            )
            Text(text = "편집", color = GrayText, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(18.dp))
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(132.dp),
        ) { page ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                pages[page].forEach { following ->
                    FollowingCard(
                        following = following,
                        modifier = Modifier.weight(1f),
                    )
                }
                repeat(3 - pages[page].size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
        PagerIndicator(
            pageCount = pages.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}

@Composable
private fun FollowingCard(
    following: FollowingUiModel,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        AsyncImage(
            model = following.avatarUrl,
            contentDescription = "${following.name} 프로필 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(84.dp)
                .background(LightGray),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = following.name,
            color = Black,
            fontSize = 11.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .size(if (index == currentPage) 8.dp else 6.dp)
                    .clip(CircleShape)
                    .background(if (index == currentPage) Black else BorderGray),
            )
        }
    }
}

@Composable
private fun LoadingProfile() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(color = Black)
    }
}

@Composable
private fun ErrorProfile(onRetryClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_user),
            contentDescription = null,
            modifier = Modifier.size(42.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "프로필 정보를 불러오지 못했습니다.",
            color = Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "네트워크 상태를 확인한 뒤 다시 시도해주세요.",
            color = GrayText,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(22.dp))
        Button(
            onClick = onRetryClick,
            colors = ButtonDefaults.buttonColors(containerColor = Black, contentColor = White),
            shape = RoundedCornerShape(24.dp),
        ) {
            Text(text = "다시 시도")
        }
    }
}

private sealed interface ProfileUiState {
    data object Loading : ProfileUiState
    data object Error : ProfileUiState
    data class Success(val profile: UserProfileUiModel) : ProfileUiState
}
