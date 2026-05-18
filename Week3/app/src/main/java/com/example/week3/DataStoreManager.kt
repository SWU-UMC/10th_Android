package com.example.week3

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "shopping_prefs")

class DataStoreManager(private val context: Context) {
    private val gson = Gson()
    private val PRODUCT_LIST_KEY = stringPreferencesKey("product_list")
    private val WISHLIST_KEY = stringPreferencesKey("wishlist_key")

    suspend fun saveAllProducts(products: List<Product>) {
        context.dataStore.edit { prefs ->
            prefs[PRODUCT_LIST_KEY] = gson.toJson(products)
        }
    }

    fun getAllProducts(): Flow<List<Product>> = context.dataStore.data.map { prefs ->
        val json = prefs[PRODUCT_LIST_KEY] ?: return@map emptyList()
        val type = object : TypeToken<List<Product>>() {}.type
        gson.fromJson(json, type)
    }


    suspend fun saveWishlist(wishlist: List<Product>) {
        context.dataStore.edit { prefs ->
            prefs[WISHLIST_KEY] = gson.toJson(wishlist)
        }
    }


    fun getWishlist(): Flow<List<Product>> = context.dataStore.data.map { prefs ->
        val json = prefs[WISHLIST_KEY] ?: return@map emptyList()
        val type = object : TypeToken<List<Product>>() {}.type
        gson.fromJson(json, type)
    }
}