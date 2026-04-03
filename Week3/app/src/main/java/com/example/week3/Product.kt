package com.example.week3

// 상품의 데이터를 묶어서 관리하는 클래스입니다.
data class Product(
    val name: String,
    val price: String,
    val imageRes: Int // R.drawable.jordan1 같은 정수값이 들어갑니다.
)