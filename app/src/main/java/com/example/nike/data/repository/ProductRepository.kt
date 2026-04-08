package com.example.nike.data.repository

import com.example.nike.data.local.ProductDataStore
import com.example.nike.data.model.Product
import com.example.nike.data.model.ProductDummyData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first

data class ProductUiModel(
    val id: Int,
    val name: String,
    val price: Int,
    val imageResId: Int,
    val category: String,
    val isNew: Boolean,
    val isLiked: Boolean
)

class ProductRepository(
    private val dataStore: ProductDataStore
) {

    val allProducts: Flow<List<ProductUiModel>> =
        combine(
            dataStore.getProducts(),
            dataStore.getWishlistIds()
        ) { products, wishlistIds ->
            products.map { product ->
                product.toUiModel(wishlistIds.contains(product.id))
            }
        }

    suspend fun initProductsIfEmpty() {
        val currentProducts = dataStore.getProducts().first()
        if (currentProducts.isEmpty()) {
            dataStore.saveProducts(ProductDummyData.getProducts())
            dataStore.saveWishlistIds(emptyList())
            dataStore.saveCartIds(emptyList())
        }
    }

    suspend fun toggleWishlist(productId: Int) {
        val currentWishlist = dataStore.getWishlistIds().first().toMutableList()

        if (currentWishlist.contains(productId)) {
            currentWishlist.remove(productId)
        } else {
            currentWishlist.add(productId)
        }

        dataStore.saveWishlistIds(currentWishlist)
    }

    suspend fun addToCart(productId: Int) {
        val currentCart = dataStore.getCartIds().first().toMutableList()
        if (!currentCart.contains(productId)) {
            currentCart.add(productId)
        }
        dataStore.saveCartIds(currentCart)
    }

    suspend fun removeFromCart(productId: Int) {
        val currentCart = dataStore.getCartIds().first().toMutableList()
        currentCart.remove(productId)
        dataStore.saveCartIds(currentCart)
    }

    suspend fun getProductById(productId: Int): ProductUiModel? {
        val products = dataStore.getProducts().first()
        val wishlistIds = dataStore.getWishlistIds().first()
        return products.find { it.id == productId }?.toUiModel(wishlistIds.contains(productId))
    }

    private fun Product.toUiModel(isLiked: Boolean): ProductUiModel {
        return ProductUiModel(
            id = id,
            name = name,
            price = price,
            imageResId = imageResId,
            category = category,
            isNew = isNew,
            isLiked = isLiked
        )
    }
}