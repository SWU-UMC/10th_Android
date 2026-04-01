package com.example.week2

import java.io.Serializable

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val imageResId: Int,
    var isWishlisted: Boolean = false,
    val category: String = ""
) : Serializable
