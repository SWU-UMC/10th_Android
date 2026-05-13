package com.example.week3

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "product_prefs")

object ProductRepository {
    private val PRODUCTS_KEY = stringPreferencesKey("products_json")
    private val gson = Gson()

    private val initialList = listOf(
        ProductData(1, R.drawable.image4, "상품1", "설명1", 1, "₩20,000"),
        ProductData(2, R.drawable.image2, "상품2", "설명2", 2, "₩40,000"),
        ProductData(3, R.drawable.image3, "상품3", "설명3", 4, "₩80,000"),
        ProductData(4, R.drawable.image1, "상품4", "설명4", 5, "₩100,000")
    )

    fun getProductsFlow(context: Context): Flow<List<ProductData>> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[PRODUCTS_KEY]
            if (json == null) {
                initialList
            } else {
                val type = object : TypeToken<List<ProductData>>() {}.type
                gson.fromJson(json, type)
            }
        }
    }

    suspend fun getProductsOnce(context: Context): List<ProductData> {
        val preferences = context.dataStore.data.first()
        val json = preferences[PRODUCTS_KEY]
        return if (json == null) {
            saveProducts(context, initialList)
            initialList
        } else {
            val type = object : TypeToken<List<ProductData>>() {}.type
            gson.fromJson(json, type)
        }
    }

    suspend fun saveProducts(context: Context, productList: List<ProductData>) {
        val jsonString = gson.toJson(productList)
        context.dataStore.edit { preferences ->
            preferences[PRODUCTS_KEY] = jsonString
        }
    }
}