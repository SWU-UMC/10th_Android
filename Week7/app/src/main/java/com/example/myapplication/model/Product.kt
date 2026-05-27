package com.example.myapplication.model

data class Product(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val price: String,
    val category: String = "All",
    val description: String = "프리미엄 라인업"
)