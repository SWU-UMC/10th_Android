package com.example.week7.ui.screen

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.week7.R
import com.example.week7.network.ApiClient
import com.example.week7.network.UserData
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val service = remember { ApiClient.service }

    var userProfile by remember { mutableStateOf<UserData?>(null) }
    var followingList by remember { mutableStateOf<List<UserData>>(emptyList()) }

    LaunchedEffect(Unit) {
        launch {
            try {
                val profileResponse = service.getProfile()
                userProfile = profileResponse.data
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, R.string.myError, Toast.LENGTH_SHORT).show()
            }
        }

        launch {
            try {
                val followingResponse = service.getFollowingList(page = 1)
                val allUsers = followingResponse.data
                followingList = allUsers.filter { it.id != 1 }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, R.string.youError, Toast.LENGTH_SHORT).show()
            }
        }
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(userProfile?.avatar)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.img_avatar),
            error = painterResource(id = R.drawable.img_avatar),
            contentDescription = stringResource(id = R.string.description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(8.dp))

        val nickname = userProfile?.let { "${it.firstName} ${it.lastName}" } ?: stringResource(id = R.string.nickname)
        Text(
            text = nickname,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(6.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = stringResource(id = R.string.edit_profile), color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ProfileMenuButton(iconRes = R.drawable.ic_order, label = stringResource(id = R.string.order))
            ProfileMenuButton(iconRes = R.drawable.ic_pass, label = stringResource(id = R.string.pass))
            ProfileMenuButton(iconRes = R.drawable.ic_event, label = stringResource(id = R.string.event))
            ProfileMenuButton(iconRes = R.drawable.ic_setting, label = stringResource(id = R.string.setting))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .background(Color(0xFFEEEEEE))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = stringResource(id = R.string.membership), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = stringResource(id = R.string.howMany), fontSize = 12.sp, color = Color.Gray)
            }
            Text(text = stringResource(id = R.string.arrow), fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.following), fontWeight = FontWeight.Bold)
            Text(
                text = stringResource(id = R.string.edit),
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.clickable { }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (followingList.isNotEmpty()) {
            val pagerState = rememberPagerState(pageCount = { followingList.size })

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(115.dp),
                    pageSize = PageSize.Fixed(90.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp),
                    pageSpacing = 4.dp
                ) { page ->
                    val user = followingList[page]
                    FollowingPagerItem(user = user)
                }

                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    repeat(followingList.size) { index ->
                        val isSelected = pagerState.currentPage == index
                        Box(
                            modifier = Modifier
                                .padding(3.dp)
                                .size(if (isSelected) 7.dp else 5.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) Color.Black else Color.LightGray)
                        )
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(115.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.outsider), color = Color.Gray, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.registerDate), color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
fun RowScope.ProfileMenuButton(iconRes: Int, label: String) {
    Column(
        modifier = Modifier
            .weight(1f)
            .clickable { }
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(26.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 14.sp)
    }
}

@Composable
fun FollowingPagerItem(user: UserData) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(user.avatar)
                .crossfade(true)
                .build(),
            contentDescription = user.firstName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = user.firstName,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}