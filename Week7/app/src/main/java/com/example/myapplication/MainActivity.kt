package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.model.Product
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf("홈") }
    val wishlistedIds = remember { mutableStateListOf<Int>() }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val items = listOf("홈", "구매하기", "위시리스트", "장바구니", "프로필")
                val icons = listOf(
                    Icons.Default.Home,
                    Icons.Default.Store,
                    Icons.Default.Favorite,
                    Icons.Default.ShoppingCart,
                    Icons.Default.Person
                )

                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedTab == item,
                        onClick = { selectedTab = item },
                        icon = { Icon(imageVector = icons[index], contentDescription = item) },
                        label = { Text(item) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                "홈" -> HomeScreen()

                "구매하기" -> StoreScreen(
                    wishlistedIds = wishlistedIds,
                    onHeartToggle = { id ->
                        if (wishlistedIds.contains(id)) {
                            wishlistedIds.remove(id)
                        } else {
                            wishlistedIds.add(id)
                        }
                    }
                )

                "위시리스트" -> WishlistScreen(
                    wishlistedIds = wishlistedIds,
                    onHeartToggle = { id ->
                        if (wishlistedIds.contains(id)) {
                            wishlistedIds.remove(id)
                        }
                    }
                )

                "장바구니" -> CartScreen(onOrderClick = { selectedTab = "구매하기" })

                "프로필" -> ProfileScreen()

                else -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("${selectedTab} 화면")
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val jordanList = listOf(
        Product(101, "AirJordan_1", R.drawable.jordan_1, "₩219,000", "Jordan"),
        Product(102, "Airjordan_2", R.drawable.jordan_2, "₩199,000", "Jordan"),
        Product(103, "AirJordan_3", R.drawable.jordan_3, "₩209,000", "Jordan")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.home_logo),
                contentDescription = "홈 브랜드 로고",
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                contentScale = ContentScale.FillWidth
            )
        }

        item {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Text(text = "What's new", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "나이키 최신 상품", color = Color.Gray, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
            }
        }

        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items = jordanList, key = { it.id }) { jordan ->
                    JordanHorizontalItem(product = jordan)
                }
            }
        }
    }
}


@Composable
fun JordanHorizontalItem(product: Product) {
    Column(
        modifier = Modifier.width(180.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
            modifier = Modifier.fillMaxWidth().height(180.dp)
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = product.name, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = Color.Black)
        Text(text = product.price, fontSize = 13.sp, color = Color.DarkGray, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun WishlistScreen(
    wishlistedIds: List<Int>,
    onHeartToggle: (Int) -> Unit
) {
    val wishlistedProducts = sampleStoreProducts.filter { wishlistedIds.contains(it.id) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp), contentAlignment = Alignment.Center) {
            Text(text = "My Wishlist", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
        }
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        if (wishlistedProducts.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Empty Wishlist",
                        tint = Color.Gray,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "위시리스트가 비어있습니다.", fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(text = "구매하기에서 하트를 눌러보세요!", color = Color.Gray, fontSize = 14.sp)
                }
            }
        } else {
            val chunkedWishlist = wishlistedProducts.chunked(2)
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(
                    items = chunkedWishlist,
                    key = { row -> row.map { it.id }.joinToString("-") }
                ) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        for (product in rowItems) {
                            Box(modifier = Modifier.weight(1f)) {
                                StoreProductItem(
                                    product = product,
                                    isLiked = true,
                                    onHeartClick = { onHeartToggle(product.id) }
                                )
                            }
                        }
                        if (rowItems.size < 2) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CartScreen(onOrderClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_cartcircle),
            contentDescription = "장바구니 아이콘",
            modifier = Modifier.size(100.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "장바구니가 비어있습니다.", fontWeight = FontWeight.Bold)
        Text(text = "제품을 추가하면 여기에 표시됩니다.", color = Color.Gray)
        Spacer(modifier = Modifier.weight(1f))
        Surface(
            modifier = Modifier.fillMaxWidth().height(56.dp).clickable { onOrderClick() },
            shape = RoundedCornerShape(28.dp),
            color = Color.Black
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "주문하기", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    var myInfo by remember { mutableStateOf<UserData?>(null) }
    var followingList by remember { mutableStateOf<List<UserData>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val userResult = RetrofitClient.service.getUser(id = 1)
            myInfo = userResult.data

            val listResult = RetrofitClient.service.getUsers(page = 2)
            followingList = listResult.data
        } catch (e: Exception) {
            errorMessage = "데이터를 불러오지 못했습니다: ${e.localizedMessage}"
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("마이페이지", fontWeight = FontWeight.Bold, fontSize = 18.sp) }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (errorMessage != null) {
                Text(text = errorMessage!!, color = Color.Red, modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        AsyncImage(
                            model = myInfo?.avatar,
                            contentDescription = "프로필 이미지",
                            modifier = Modifier.size(100.dp).clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        val fullName = "${myInfo?.first_name} ${myInfo?.last_name}"
                        Text(text = fullName, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        Spacer(modifier = Modifier.height(16.dp))
                    }


                    item {
                        Surface(
                            modifier = Modifier
                                .width(160.dp)
                                .height(40.dp)
                                .clickable { },
                            shape = RoundedCornerShape(20.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray),
                            color = Color.White
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(text = "프로필 수정", fontSize = 14.sp, color = Color.Black, fontWeight = FontWeight.Medium)
                            }
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                        HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                    }


                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MenuComponent(icon = Icons.Default.Inventory, label = "주문", modifier = Modifier.weight(1f))
                            VerticalDivider(modifier = Modifier.height(32.dp), color = Color(0xFFE0E0E0), thickness = 1.dp)
                            MenuComponent(icon = Icons.Default.Badge, label = "패스", modifier = Modifier.weight(1f))
                            VerticalDivider(modifier = Modifier.height(32.dp), color = Color(0xFFE0E0E0), thickness = 1.dp)
                            MenuComponent(icon = Icons.Default.CalendarMonth, label = "이벤트", modifier = Modifier.weight(1f))
                            VerticalDivider(modifier = Modifier.height(32.dp), color = Color(0xFFE0E0E0), thickness = 1.dp)
                            MenuComponent(icon = Icons.Default.Settings, label = "설정", modifier = Modifier.weight(1f))
                        }
                        HorizontalDivider(color = Color(0xFFF5F5F5), thickness = 8.dp)
                    }


                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { }
                                .padding(horizontal = 20.dp, vertical = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(text = "나이키 멤버 혜택", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "0개 사용 가능", fontSize = 13.sp, color = Color.Gray)
                            }
                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = "더보기",
                                tint = Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        HorizontalDivider(color = Color(0xFFF5F5F5), thickness = 8.dp)
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "팔로잉 (${followingList.size})", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                Text(text = "편집", fontSize = 14.sp, color = Color.Gray, modifier = Modifier.clickable { })
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            if (followingList.isNotEmpty()) {

                                val pagerState = rememberPagerState(pageCount = { followingList.size })

                                HorizontalPager(
                                    state = pagerState,
                                    modifier = Modifier.fillMaxWidth().height(110.dp),
                                    contentPadding = PaddingValues(horizontal = 140.dp),
                                    pageSpacing = 16.dp
                                ) { page ->
                                    val user = followingList[page]


                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        AsyncImage(
                                            model = user.avatar,
                                            contentDescription = "팔로잉 프로필",
                                            modifier = Modifier
                                                .size(70.dp)
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = user.first_name,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = Color.Black,
                                            maxLines = 1
                                        )
                                    }
                                }
                            } else {
                                Text("팔로잉하는 유저가 없습니다.", color = Color.Gray, fontSize = 14.sp)
                            }

                            Spacer(modifier = Modifier.height(32.dp))
                            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                Text(text = "회원 가입일: 2025년 9월", color = Color.LightGray, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MenuComponent(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.clickable { },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = Color.Gray, modifier = Modifier.size(28.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = label, fontSize = 13.sp, color = Color.Black, fontWeight = FontWeight.Medium)
    }
}