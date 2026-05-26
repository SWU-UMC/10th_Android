package com.example.week7.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.week7.ui.components.ProductData
import com.example.week7.R
import androidx.core.content.edit

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences = application.getSharedPreferences("wishlist_prefs", Context.MODE_PRIVATE)

    private val _productList = mutableStateListOf<ProductData>()
    val productList: List<ProductData> get() = _productList

    init {
        val savedWishIds = getSavedWishlist()

        val initialData = listOf(
            ProductData(1, R.drawable.img_nemo, "상품1", "설명1", 1, "₩20,000"),
            ProductData(2, R.drawable.image2, "상품2", "설명2", 2, "₩40,000"),
            ProductData(3, R.drawable.image3, "상품3", "설명3", 4, "₩80,000"),
            ProductData(4, R.drawable.image1, "상품4", "설명4", 5, "₩100,000")
        ).map { product ->
            product.copy(isWished = savedWishIds.contains(product.id.toString()))
        }

        _productList.addAll(initialData)
    }

    fun toggleWishStatus(productId: Int) {
        val index = _productList.indexOfFirst { it.id == productId }
        if (index != -1) {
            val currentItem = _productList[index]
            val newWishStatus = !currentItem.isWished

            _productList[index] = currentItem.copy(isWished = newWishStatus)

            saveWishStatus(productId, newWishStatus)
        }
    }
    private fun getSavedWishlist(): Set<String> {
        return sharedPreferences.getStringSet("wish_ids", emptySet()) ?: emptySet()
    }

    private fun saveWishStatus(productId: Int, isWished: Boolean) {
        val currentIds = getSavedWishlist().toMutableSet()

        if (isWished) {
            currentIds.add(productId.toString())
        } else {
            currentIds.remove(productId.toString())
        }

        sharedPreferences.edit {
            putStringSet("wish_ids", currentIds)
        }
    }
}