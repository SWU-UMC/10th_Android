package com.example.myapplication.model

data class StoreProduct(
    val id: Int,
    val name: String,         // 예: "Adidas", "Nike" 등 첫 글자 대문자로 표시할 이름
    val imageRes: Int,        // drawable 이미지 리소스 ID
    val price: String,        // 예: "$89.00"
    val category: String      // "Tops & Shirts" 또는 "Sale"
)