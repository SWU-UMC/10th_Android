package com.example.week7.ui

import com.example.week7.R

sealed class BottomNavItem(
    val title: String,
    val icon: Int,
    val route: Any
) {
    data object Home : BottomNavItem("홈", R.drawable.ic_housesimple, Screen.Home)
    data object Shop : BottomNavItem("구매하기", R.drawable.ic_listmagnifyingglass, Screen.Shop)
    data object Wishlist : BottomNavItem("위시리스트", R.drawable.ic_heartstraight, Screen.Wishlist)
    data object Cart : BottomNavItem("장바구니", R.drawable.ic_bagsimple, Screen.Cart)
    data object Profile : BottomNavItem("프로필", R.drawable.ic_user, Screen.Profile)
}
