package com.example.week7.ui.components

data class ProductData(
    val id: Int,
    val imageRes: Int,
    val name: String,
    val desc: String,
    val colorCount: Int,
    val price: String,
    val isWished: Boolean = false
)