package com.example.week2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week2.Product
import com.example.week2.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productId = MutableStateFlow<Int?>(null)

    val product: StateFlow<Product?> = combine(_productId, productRepository.products) { id, products ->
        products.find { it.id == id }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun setProductId(id: Int) {
        _productId.value = id
    }

    fun toggleWishlist() {
        val currentProduct = product.value ?: return
        viewModelScope.launch {
            productRepository.updateWishlistStatus(currentProduct.id, !currentProduct.isWishlisted)
        }
    }
}
