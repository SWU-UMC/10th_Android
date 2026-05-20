package com.example.nike.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.nike.data.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "shopping_prefs")

class ProductDataStore(private val context: Context) {

    companion object {
        private val PRODUCTS_KEY = stringPreferencesKey("products_json")
        private val WISHLIST_KEY = stringPreferencesKey("wishlist_json")
        private val CART_KEY = stringPreferencesKey("cart_json")
    }

    private val gson = Gson()

    fun getProducts(): Flow<List<Product>> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[PRODUCTS_KEY] ?: "[]"
            val type = object : TypeToken<List<Product>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        }
    }

    fun getWishlistIds(): Flow<List<Int>> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[WISHLIST_KEY] ?: "[]"
            val type = object : TypeToken<List<Int>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        }
    }

    fun getCartIds(): Flow<List<Int>> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[CART_KEY] ?: "[]"
            val type = object : TypeToken<List<Int>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        }
    }

    suspend fun saveProducts(products: List<Product>) {
        context.dataStore.edit { preferences ->
            preferences[PRODUCTS_KEY] = gson.toJson(products)
        }
    }

    suspend fun saveWishlistIds(ids: List<Int>) {
        context.dataStore.edit { preferences ->
            preferences[WISHLIST_KEY] = gson.toJson(ids)
        }
    }

    suspend fun saveCartIds(ids: List<Int>) {
        context.dataStore.edit { preferences ->
            preferences[CART_KEY] = gson.toJson(ids)
        }
    }
}