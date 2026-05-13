package com.example.week2

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.week2.ui.theme.NikeBlack
import com.example.week2.ui.theme.NikeGray

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            CustomBottomBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(onProductClick = { /* Handle detail */ })
            }
            composable(Screen.Purchase.route) {
                PurchaseScreen(onProductClick = { /* Handle detail */ })
            }
            composable(Screen.Wishlist.route) {
                WishlistScreen(onProductClick = { /* Handle detail */ })
            }
            composable(Screen.Cart.route) {
                CartScreen(onOrderClick = {
                    navController.navigate(Screen.Purchase.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

@Composable
fun CustomBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        Screen.items.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = screen.title,
                        modifier = Modifier.size(24.dp),
                        tint = if (selected) NikeBlack else NikeGray
                    )
                },
                label = {
                    Text(
                        text = screen.title,
                        color = if (selected) NikeBlack else NikeGray
                    )
                },
                selected = selected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
