package com.example.week2

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable data object HomeRoute
@Serializable data object CartRoute
@Serializable data object PurchaseRoute
@Serializable data object WishlistRoute
@Serializable data object ProfileRoute
@Serializable data class ProductDetailRoute(val productId: Int)

private data class BottomNavItem(
    val labelRes: Int,
    val iconRes: Int,
    val route: Any,
    val routeClass: KClass<*>
)

private val bottomNavItems = listOf(
    BottomNavItem(R.string.nav_home, R.drawable.ic_housesimple, HomeRoute, HomeRoute::class),
    BottomNavItem(R.string.nav_purchase, R.drawable.ic_listmagnifyingglass, PurchaseRoute, PurchaseRoute::class),
    BottomNavItem(R.string.nav_wishlist, R.drawable.ic_heartstraight, WishlistRoute, WishlistRoute::class),
    BottomNavItem(R.string.nav_cart, R.drawable.ic_bagsimple, CartRoute, CartRoute::class),
    BottomNavItem(R.string.nav_profile, R.drawable.ic_user, ProfileRoute, ProfileRoute::class)
)

@Composable
fun AppNavigation(viewModel: ProductViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDest = navBackStackEntry?.destination

    val showBottomBar = currentDest?.run {
        hasRoute<HomeRoute>() || hasRoute<CartRoute>() || hasRoute<PurchaseRoute>() ||
            hasRoute<WishlistRoute>() || hasRoute<ProfileRoute>()
    } ?: false

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                AppBottomBar(navController = navController, currentDest = currentDest)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = HomeRoute,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<HomeRoute> {
                HomeScreen(
                    viewModel = viewModel,
                    onNavigateToDetail = { productId ->
                        navController.navigate(ProductDetailRoute(productId))
                    }
                )
            }
            composable<CartRoute> {
                CartScreen(onNavigateToPurchase = {
                    navController.navigate(PurchaseRoute) {
                        popUpTo<HomeRoute> { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
            }
            composable<PurchaseRoute> {
                PurchaseScreen(
                    viewModel = viewModel,
                    onNavigateToDetail = { productId ->
                        navController.navigate(ProductDetailRoute(productId))
                    }
                )
            }
            composable<WishlistRoute> {
                WishlistScreen(viewModel = viewModel)
            }
            composable<ProfileRoute> {
                ProfileScreen()
            }
            composable<ProductDetailRoute> { backStackEntry ->
                val route = backStackEntry.toRoute<ProductDetailRoute>()
                ProductDetailScreen(
                    productId = route.productId,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
private fun AppBottomBar(
    navController: NavController,
    currentDest: NavDestination?
) {
    NavigationBar {
        bottomNavItems.forEach { item ->
            val isSelected = currentDest?.hasRoute(item.routeClass) == true
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo<HomeRoute> { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = stringResource(id = item.labelRes)
                    )
                },
                label = { Text(text = stringResource(id = item.labelRes)) }
            )
        }
    }
}
