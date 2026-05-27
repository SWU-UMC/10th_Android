package com.example.week9.model

import androidx.compose.ui.graphics.Color

interface ProductUiModel {
    val id: Int
    val name: String
    val description: String
    val colorCount: String
    val price: String
    val shortCategory: String
    val detail: String
    val backgroundColor: Color
    val imageRes: Int
    val tintImage: Boolean
    val isBestSeller: Boolean
    val defaultWishlisted: Boolean
}

data class Product(
    override val id: Int,
    override val name: String,
    override val description: String,
    override val colorCount: String,
    override val price: String,
    override val shortCategory: String,
    override val detail: String,
    override val backgroundColor: Color,
    override val imageRes: Int,
    override val tintImage: Boolean = true,
    override val isBestSeller: Boolean = false,
    override val defaultWishlisted: Boolean = false,
) : ProductUiModel


