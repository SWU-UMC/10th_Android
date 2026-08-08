package com.example.week2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week2.Product
import com.example.week2.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    val allProducts: StateFlow<List<Product>> = productRepository.products
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun toggleWishlist(product: Product) {
        viewModelScope.launch {
            productRepository.updateWishlistStatus(product.id, !product.isWishlisted)
        }
    }
}
