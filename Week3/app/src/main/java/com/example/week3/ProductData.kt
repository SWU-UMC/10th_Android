package com.example.week3

data class ProductData (
    val id: Int,
    val imageRes: String,
    val name: String,
    val desc: String,
    val colorCount: Int,
    val price: String,
    var isWished: Boolean = false
)