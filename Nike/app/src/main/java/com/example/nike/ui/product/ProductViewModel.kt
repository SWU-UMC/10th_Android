package com.example.nike.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nike.data.repository.ProductUiModel
import com.example.nike.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    val allProducts: StateFlow<List<ProductUiModel>> = repository.allProducts.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

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
