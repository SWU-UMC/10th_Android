package com.example.week7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.navigation
import com.example.week7.navigation.Route
import com.example.week7.ui.screen.*
import androidx.compose.foundation.background

data class NavigationItem(
    val title: String,
    val route: Any,
    val iconRes: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                MainView()
            }
        }
    }
}

@Composable
fun MainView() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navItems = listOf(
        NavigationItem("Home", Route.Home, R.drawable.ic_home),
        NavigationItem("Buy", Route.BuyGraph, R.drawable.ic_buy),
        NavigationItem("Wish", Route.Wishlist, R.drawable.ic_wishlist),
        NavigationItem("Cart", Route.ShoppingCart, R.drawable.ic_shoppingcart),
        NavigationItem("Profile", Route.Profile, R.drawable.ic_profile)
    )

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
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
            composable<Route.Home> { HomeScreen() }

            navigation<Route.BuyGraph>(startDestination = Route.BuyGraph.All) {
                composable<Route.BuyGraph.All> {
                    BuyScreen()
                }
            }

            composable<Route.Wishlist> { WishlistScreen() }

            composable<Route.ShoppingCart> {
                ShoppingCartScreen(navController = navController)
            }

            composable<Route.Profile> { ProfileScreen() }
        }
    }
}