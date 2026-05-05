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

    val productsFlow: Flow<List<Product>> = context.dataStore.data.map { preferences ->
        val jsonString = preferences[PRODUCTS_KEY] ?: ""
        if (jsonString.isEmpty()) {
            getInitialProducts()
        } else {
            val type = object : TypeToken<List<Product>>() {}.type
            gson.fromJson(jsonString, type)
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
