package com.example.week7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.week7.ui.*
import com.example.week7.ui.theme.Week7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // dynamicColor = false로 설정하여 시스템 테마 색상이 아닌 흰색 배경을 유지
            Week7Theme(dynamicColor = false) {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        containerColor = Color.White, // Scaffold 배경을 흰색으로 고정
        bottomBar = { BottomNavigation(navController = navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(navController = navController)
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Shop,
        BottomNavItem.Wishlist,
        BottomNavItem.Cart,
        BottomNavItem.Profile
    )
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black,
        tonalElevation = 0.dp, // M3 기본 톤 오버레이 제거
        modifier = Modifier.drawBehind {
            drawIntoCanvas { canvas ->
                val paint = Paint().asFrameworkPaint()
                // CSS: 0px -4px 20px 0px #00000008
                paint.setShadowLayer(
                    20f,      // blur
                    0f,       // dx
                    -4f,      // dy
                    android.graphics.Color.argb(8, 0, 0, 0) // #00000008
                )
                canvas.nativeCanvas.drawRect(
                    0f,
                    0f,
                    size.width,
                    size.height,
                    paint
                )
            }
        }
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title, fontSize = 10.sp) },
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.Black,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomNavItem.Shop.screenRoute) {
            ShopScreen()
        }
        composable(BottomNavItem.Wishlist.screenRoute) {
            WishlistScreen()
        }
        composable(BottomNavItem.Cart.screenRoute) {
            CartScreen(onOrderClick = {
                navController.navigate(BottomNavItem.Shop.screenRoute) {
                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
        }
        composable(BottomNavItem.Profile.screenRoute) {
            ProfileScreen()
        }
    }
}
