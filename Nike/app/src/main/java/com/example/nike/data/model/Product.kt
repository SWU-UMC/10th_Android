package com.example.nike.data.model

data class Product(
    val id: Int,
    val name: String,
    val description: String = "",
    val price: Int,
    val imageResId: Int,
    val colorCount: Int = 0,
    val isBestSeller: Boolean = false,
    var isLiked: Boolean = false,
    val category: String = "TOP",
    val isNew: Boolean = false
)