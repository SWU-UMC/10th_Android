package com.example.nike.ui.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.nike.data.local.ProductDataStore
import com.example.nike.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProductRepository(ProductDataStore(application.applicationContext))

    val allProducts = repository.allProducts.asLiveData()

    init {
        viewModelScope.launch {
            repository.initProductsIfEmpty()
        }
    }

    fun toggleWishlist(productId: Int) {
        viewModelScope.launch {
            repository.toggleWishlist(productId)
        }
    }

    fun addToCart(productId: Int) {
        viewModelScope.launch {
            repository.addToCart(productId)
        }
    }
}
