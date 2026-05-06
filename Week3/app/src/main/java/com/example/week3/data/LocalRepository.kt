package com.example.week3.data

import com.example.week3.DataStoreManager
import com.example.week3.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val dataStore: DataStoreManager
) {

    fun getAllProducts(): Flow<List<Product>> = dataStore.getAllProducts()

    fun getWishlist(): Flow<List<Product>> = dataStore.getWishlist()

    suspend fun saveWishlist(list: List<Product>) {
        dataStore.saveWishlist(list)
    }
}