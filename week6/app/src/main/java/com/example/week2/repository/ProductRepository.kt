package com.example.week2.repository

import com.example.week2.DataStoreManager
import com.example.week2.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    val products: Flow<List<Product>> = dataStoreManager.productsFlow

    suspend fun updateWishlistStatus(productId: Int, isWishlisted: Boolean) {
        dataStoreManager.updateWishlistStatus(productId, isWishlisted)
    }

    suspend fun saveProducts(products: List<Product>) {
        dataStoreManager.saveProducts(products)
    }
}
