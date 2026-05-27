package com.example.week8.data

import androidx.compose.ui.graphics.Color
import com.example.week8.R
import com.example.week8.model.Product

val week8Products = listOf(
    Product(
        id = 1,
        name = "Nike Everyday Plus Cushioned",
        description = "Training Ankle Socks (6 Pairs)",
        colorCount = "5 Colours",
        price = "US$10",
        shortCategory = "Training Crew Socks",
        detail = "The Nike Everyday Plus Cushioned Socks bring comfort to your workout with extra cushioning under the heel and forefoot and a snug, supportive arch band. Sweat-wicking power and breathability up top help keep your feet dry and cool.",
        backgroundColor = Color(0xFFF5F5F5),
        imageRes = R.drawable.home_logo,
        tintImage = false,
        defaultWishlisted = true,
    ),
    Product(
        id = 2,
        name = "Nike Elite Crew",
        description = "Basketball Socks",
        colorCount = "7 Colours",
        price = "US$16",
        shortCategory = "Basketball Socks",
        detail = "A lightweight everyday sock with soft cushioning and breathable comfort for repeated training sessions.",
        backgroundColor = Color(0xFFE8E8E8),
        imageRes = R.drawable.ic_home,
    ),
    Product(
        id = 3,
        name = "Nike Air Force 1 '07",
        description = "Women's Shoes",
        colorCount = "5 Colours",
        price = "US$115",
        shortCategory = "Women's Shoes",
        detail = "Classic hoops style meets crisp leather, bold details and just the right amount of shine.",
        backgroundColor = Color(0xFFB8B8B8),
        imageRes = R.drawable.ic_bag_simple,
        isBestSeller = true,
    ),
    Product(
        id = 4,
        name = "Jordan ENike Air Force 1 '07essentials",
        description = "Men's Shoes",
        colorCount = "2 Colours",
        price = "US$115",
        shortCategory = "Men's Shoes",
        detail = "A clean everyday sneaker inspired by court style and built for all-day comfort.",
        backgroundColor = Color(0xFF444444),
        imageRes = R.drawable.ic_user,
        isBestSeller = true,
    ),
    Product(
        id = 5,
        name = "Air Jordan 1 Mid",
        description = "",
        colorCount = "",
        price = "US$125",
        shortCategory = "Men's Shoes",
        detail = "Premium materials and classic Jordan details deliver a timeless mid-top look.",
        backgroundColor = Color(0xFFC9C9C9),
        imageRes = R.drawable.home_logo,
        tintImage = false,
        defaultWishlisted = true,
    ),
)
