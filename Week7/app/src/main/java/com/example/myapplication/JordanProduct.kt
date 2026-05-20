package com.example.myapplication
data class JordanProduct(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val price: String,
    val description: String = "나이키 프리미엄 라인업"
)