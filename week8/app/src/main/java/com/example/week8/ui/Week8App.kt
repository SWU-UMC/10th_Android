package com.example.week8.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.week8.data.week8Products
import com.example.week8.navigation.BottomTab
import com.example.week8.navigation.Route
import com.example.week8.ui.cart.CartScreen
import com.example.week8.ui.component.Week8BottomBar
import com.example.week8.ui.detail.ProductDetailScreen
import com.example.week8.ui.home.HomeScreen
import com.example.week8.ui.profile.ProfileScreen
import com.example.week8.ui.shop.ShopScreen
import com.example.week8.ui.wishlist.WishlistScreen

@Composable
fun Week8App() {
    val navController = rememberNavController()
    val products = remember { week8Products }
    var wishlistedIds by rememberSaveable {
        mutableStateOf(products.filter { it.defaultWishlisted }.map { it.id })
    }

    fun toggleWishlist(productId: Int) {
        wishlistedIds = if (productId in wishlistedIds) {
            wishlistedIds - productId
        } else {
            wishlistedIds + productId
        }
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedTab = navBackStackEntry?.destination?.toBottomTab()

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            Week8BottomBar(
                selectedTab = selectedTab,
                onTabClick = { tab ->
                    navController.navigate(tab.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding),
        ) {
            NavHost(
                navController = navController,
                startDestination = Route.Home,
            ) {
                composable<Route.Home> {
                    HomeScreen(
                        products = products,
                        onProductClick = { product ->
                            navController.navigate(Route.ProductDetail(product.id))
                        },
                    )
                }
                composable<Route.Shop> {
                    ShopScreen(
                        products = products,
                        wishlistedIds = wishlistedIds,
                        onProductClick = { product ->
                            navController.navigate(Route.ProductDetail(product.id))
                        },
                        onWishClick = { product -> toggleWishlist(product.id) },
                    )
                }
                composable<Route.Wishlist> {
                    WishlistScreen(
                        products = products.filter { it.id in wishlistedIds },
                        onProductClick = { product ->
                            navController.navigate(Route.ProductDetail(product.id))
                        },
                    )
                }
                composable<Route.Cart> {
                    CartScreen(
                        onOrderClick = { navController.navigate(Route.Shop) },
                    )
                }
                composable<Route.Profile> {
                    ProfileScreen()
                }
                composable<Route.ProductDetail> { backStackEntry ->
                    val route = backStackEntry.toRoute<Route.ProductDetail>()
                    val product = products.firstOrNull { it.id == route.productId }
                    if (product != null) {
                        ProductDetailScreen(
                            product = product,
                            isWishlisted = product.id in wishlistedIds,
                            onBackClick = { navController.popBackStack() },
                            onWishClick = { toggleWishlist(product.id) },
                        )
                    }
                }
            }
        }
    }
}

private fun NavDestination.toBottomTab(): BottomTab? =
    BottomTab.entries.firstOrNull { tab -> hasRoute(tab.routeClass) }
