package com.example.nike.domain.repository

import com.example.nike.data.repository.ProductUiModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    val allProducts: Flow<List<ProductUiModel>>

    suspend fun initProductsIfEmpty()
    suspend fun toggleWishlist(productId: Int)
    suspend fun addToCart(productId: Int)
    suspend fun removeFromCart(productId: Int)
    suspend fun getProductById(productId: Int): ProductUiModel?
}
