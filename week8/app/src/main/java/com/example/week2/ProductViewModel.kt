package com.example.week2

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ProductViewModel : ViewModel() {

    private val _allProducts = mutableStateListOf(
        Product(1, "Air Jordan XXXVI", "Basketball Shoes", "US$185", R.drawable.img_air_jordan_xxxvi, false, "Basketball Shoes"),
        Product(2, "Nike Air Force 1 '07", "Men's Shoes", "US$115", R.drawable.img_nike_air_force, false, "Men's Shoes"),
        Product(3, "Nike Everyday Plus Cushioned", "Training Socks", "US$20", R.drawable.img_nike_everyday_plus_cushioned, false, "Training Socks"),
        Product(4, "Nike Everyday Plus Cushioned", "Training Crew Socks (6 Pairs)", "US$10", R.drawable.img_nike_everyday_plus_cushioned, false, "Training Crew Socks"),
        Product(5, "Nike Elite Crew", "Basketball Socks", "US$16", R.drawable.img_training_ankle_socks, false, "Basketball Socks"),
        Product(6, "Nike Air Force 1 '07", "Women's Shoes", "US$115", R.drawable.img_nike_air_force, false, "Women's Shoes"),
        Product(7, "Jordan Nike Air Force 1 '07 Essentials", "Men's Shoes", "US$115", R.drawable.img_air_jordan_xxxvi, false, "Men's Shoes"),
        Product(8, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)", "US$16", R.drawable.img_training_ankle_socks, true, "Training Socks"),
        Product(9, "Air Jordan XXXVI", "Basketball Shoes", "US$185", R.drawable.img_air_jordan_xxxvi, true, "Basketball Shoes")
    )

    val homeProducts: List<Product>
        get() = _allProducts.filter { it.id <= 3 }

    val purchaseProducts: List<Product>
        get() = _allProducts.filter { it.id in 4..7 }

    val wishlistProducts: List<Product>
        get() = _allProducts.filter { it.isWishlisted }

    fun getProduct(id: Int): Product? = _allProducts.find { it.id == id }

    fun toggleWishlist(id: Int) {
        val index = _allProducts.indexOfFirst { it.id == id }
        if (index != -1) {
            _allProducts[index] = _allProducts[index].copy(isWishlisted = !_allProducts[index].isWishlisted)
        }
    }
}
