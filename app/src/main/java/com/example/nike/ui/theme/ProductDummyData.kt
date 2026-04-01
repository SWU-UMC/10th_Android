package com.example.nike.ui.theme

import com.example.nike.R

object ProductDummyData {

    fun getHomeProducts(): List<Product> {
        return listOf(
            Product(
                imageResId = R.drawable.air_jordan_xxxvi,
                name = "Air Jordan XXXVI",
                price = "US$185"
            ),
            Product(
                imageResId = R.drawable.nike_air_force,
                name = "Nike Air Force 1 '07",
                price = "US$115"
            ),
            Product(
                imageResId = R.drawable.air_jordan_xxxvi,
                name = "Jordan Essentials",
                price = "US$95"
            )
        )
    }

    fun getShopProducts(): MutableList<Product> {
        return mutableListOf(
            Product(
                imageResId = R.drawable.nike_air_force,
                name = "Nike Everyday Plus Cushioned",
                description = "Training Ankle Socks (6 Pairs)",
                colorCount = "5 Colours",
                price = "US$10",
                isLiked = false
            ),
            Product(
                imageResId = R.drawable.air_jordan_xxxvi,
                name = "Nike Elite Crew",
                description = "Basketball Socks",
                colorCount = "7 Colours",
                price = "US$16",
                isLiked = false
            ),
            Product(
                imageResId = R.drawable.nike_air_force,
                name = "Nike Air Force 1 '07",
                description = "Women's Shoes",
                colorCount = "5 Colours",
                price = "US$115",
                isBestSeller = true,
                isLiked = false
            ),
            Product(
                imageResId = R.drawable.air_jordan_xxxvi,
                name = "Jordan Essentials",
                description = "Men's Shoes",
                colorCount = "2 Colours",
                price = "US$115",
                isBestSeller = true,
                isLiked = false
            )
        )
    }

    fun getWishlistDummyProducts(): MutableList<Product> {
        return mutableListOf(
            Product(
                imageResId = R.drawable.air_jordan_xxxvi,
                name = "Air Jordan 1 Mid",
                price = "US$125",
                isLiked = true
            ),
            Product(
                imageResId = R.drawable.nike_air_force,
                name = "Nike Everyday Plus Cushioned",
                description = "Training Ankle Socks (6 Pairs)",
                colorCount = "5 Colours",
                price = "US$10",
                isLiked = true
            )
        )
    }
}