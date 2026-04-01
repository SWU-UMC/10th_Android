package com.example.nike.ui.theme

data class Product(
    val imageResId: Int,
    val name: String,
    val description: String = "",
    val colorCount: String = "",
    val price: String,
    val isBestSeller: Boolean = false,
    var isLiked: Boolean = false
)