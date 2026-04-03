package com.example.week3

// 클릭했을 때 상품 이름과 가격을 상세 페이지로 전달하기 위한 인터페이스입니다.
interface ProductClickListener {
    fun onProductClick(productName: String, price: String)
}