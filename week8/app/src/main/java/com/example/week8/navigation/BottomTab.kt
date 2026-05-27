package com.example.week8.navigation

import com.example.week8.R
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
enum class BottomTab(
    val label: String,
    val iconRes: Int,
    val route: Route,
    val routeClass: KClass<out Route>,
) {
    Home("홈", R.drawable.ic_home, Route.Home, Route.Home::class),
    Shop("구매하기", R.drawable.ic_list_magnifying_glass, Route.Shop, Route.Shop::class),
    Wishlist("위시리스트", R.drawable.ic_heart_straight, Route.Wishlist, Route.Wishlist::class),
    Cart("장바구니", R.drawable.ic_bag_simple, Route.Cart, Route.Cart::class),
    Profile("프로필", R.drawable.ic_user, Route.Profile, Route.Profile::class),
}
