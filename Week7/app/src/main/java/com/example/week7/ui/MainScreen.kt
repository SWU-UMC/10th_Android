package com.example.week7.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.week7.R
import com.example.week7.navigation.Route
import com.example.week7.navigation.NavigationItem
import com.example.week7.ui.screen.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week7.viewmodel.MainViewModel
import androidx.compose.ui.res.stringResource

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navItems = listOf(
        NavigationItem(stringResource(R.string.btnHome), Route.Home, R.drawable.ic_home),
        NavigationItem(stringResource(R.string.btnBuy), Route.BuyGraph, R.drawable.ic_buy),
        NavigationItem(stringResource(R.string.btnWishlist), Route.Wishlist, R.drawable.ic_wishlist),
        NavigationItem(stringResource(R.string.btnShoppingcart), Route.ShoppingCart, R.drawable.ic_shoppingcart),
        NavigationItem(stringResource(R.string.btnProfile), Route.Profile, R.drawable.ic_profile)
    )

    val mainViewModel: MainViewModel = viewModel()

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                navItems.forEach { item ->
                    val isSelected = currentDestination?.hierarchy?.any {
                        it.hasRoute(item.route::class)
                    } == true

                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = item.iconRes),
                                contentDescription = item.title,
                                modifier = Modifier.size(24.dp),
                                tint = if (isSelected) Color.Black else Color.Gray
                            )
                        },
                        label = { Text(item.title) },
                        selected = isSelected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Home,
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.White)
        ) {
            composable<Route.Home> {
                HomeScreen(viewModel = mainViewModel)
            }

            navigation<Route.BuyGraph>(startDestination = Route.BuyGraph.All) {
                composable<Route.BuyGraph.All> {
                    BuyScreen(viewModel = mainViewModel)
                }
            }

            composable<Route.Wishlist> {
                WishlistScreen(
                    viewModel = mainViewModel
                )
            }

            composable<Route.ShoppingCart> {
                ShoppingCartScreen(
                    onNavigateToBuy = {
                        navController.navigate(Route.BuyGraph) {
                            popUpTo<Route.ShoppingCart> { inclusive = true }
                        }
                    }
                )
            }

            composable<Route.Profile> { ProfileScreen() }
        }
    }
}