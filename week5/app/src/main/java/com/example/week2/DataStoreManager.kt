package com.example.week2

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager(private val context: Context) {
    private val gson = Gson()

    companion object {
        private val PRODUCTS_KEY = stringPreferencesKey("products")
    }

    suspend fun saveProducts(products: List<Product>) {
        val jsonString = gson.toJson(products)
        context.dataStore.edit { preferences ->
            preferences[PRODUCTS_KEY] = jsonString
        }
    }

    // 리소스 ID 꼬임 방지를 위해, 저장된 데이터가 있더라도 리소스 ID는 초기 데이터에서 가져와 매핑.
    val productsFlow: Flow<List<Product>> = context.dataStore.data.map { preferences ->
        val jsonString = preferences[PRODUCTS_KEY] ?: ""
        val initialProducts = getInitialProducts()
        
        if (jsonString.isEmpty()) {
            initialProducts
        } else {
            val type = object : TypeToken<List<Product>>() {}.type
            val savedProducts: List<Product> = gson.fromJson(jsonString, type)
            
            // 저장된 데이터의 위시리스트 상태는 유지하되, 이미지 리소스 ID는 현재 프로젝트의 ID로 갱신
            savedProducts.map { saved ->
                val initial = initialProducts.find { it.id == saved.id }
                saved.copy(imageResId = initial?.imageResId ?: saved.imageResId)
            }
        }
    }

    private fun getInitialProducts(): List<Product> {
        return listOf(
            Product(1, "Air Jordan XXXVI", "Basketball Shoes", "US$185", R.drawable.img_air_jordan_xxxvi, category = "Basketball Shoes"),
            Product(2, "Nike Air Force 1 '07", "Men's Shoes", "US$115", R.drawable.img_nike_air_force, category = "Men's Shoes"),
            Product(3, "Nike Everyday Plus Cushioned", "Training Socks", "US$20", R.drawable.img_nike_everyday_plus_cushioned, category = "Training Socks"),
            Product(4, "Nike Elite Crew", "Basketball Socks", "US$16", R.drawable.img_training_ankle_socks, category = "Basketball Socks"),
            Product(5, "Jordan Nike Air Force 1 '07 Essentials", "Men's Shoes", "US$115", R.drawable.img_air_jordan_xxxvi, category = "Men's Shoes")
        )
    }

    suspend fun updateWishlistStatus(productId: Int, isWishlisted: Boolean) {
        val products = productsFlow.first().toMutableList()
        val index = products.indexOfFirst { it.id == productId }
        if (index != -1) {
            products[index] = products[index].copy(isWishlisted = isWishlisted)
            saveProducts(products)
        }
    }
}
