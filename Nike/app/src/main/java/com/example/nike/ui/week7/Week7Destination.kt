package com.example.nike.ui.week7

import androidx.annotation.DrawableRes
import com.example.nike.R

enum class Week7Destination(
    val label: String,
    @param:DrawableRes val iconResId: Int,
) {
    Home("홈", R.drawable.ic_housesimple),
    Shop("구매하기", R.drawable.ic_listmagnifyingglass),
    Wishlist("위시리스트", R.drawable.ic_heartstraight),
    Cart("장바구니", R.drawable.ic_bagsimple),
    Profile("프로필", R.drawable.ic_user),
}
