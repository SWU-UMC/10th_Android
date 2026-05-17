package com.example.week7.ui

import com.example.week7.R

sealed class BottomNavItem(
    val title: String,
    val icon: Int,
    val screenRoute: String
) {
    object Home : BottomNavItem("홈", R.drawable.ic_housesimple, "home")
    object Shop : BottomNavItem("구매하기", R.drawable.ic_listmagnifyingglass, "shop")
    object Wishlist : BottomNavItem("위시리스트", R.drawable.ic_heartstraight, "wishlist")
    object Cart : BottomNavItem("장바구니", R.drawable.ic_bagsimple, "cart")
    object Profile : BottomNavItem("프로필", R.drawable.ic_user, "profile")
}
