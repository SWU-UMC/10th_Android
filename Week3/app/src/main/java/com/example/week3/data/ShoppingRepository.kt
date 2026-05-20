package com.example.week3.data

import com.example.week3.Product
import com.example.week3.UserListResponse
import com.example.week3.UserResponse
import kotlinx.coroutines.flow.first
import retrofit2.Callback
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    private val local: LocalRepository,
    private val remote: RemoteRepository
) {

    fun getAllProducts() = local.getAllProducts()

    fun getWishlist() = local.getWishlist()

    suspend fun toggleWishlist(product: Product) {
        val list = local.getWishlist().first().toMutableList()

        if (list.any { it.name == product.name }) {
            list.removeAll { it.name == product.name }
        } else {
            list.add(product)
        }

        local.saveWishlist(list)
    }

    fun getUser(userId: Int, callback: Callback<UserResponse>) {
        remote.getUser(userId, callback)
    }

    fun getUsers(callback: Callback<UserListResponse>) {
        remote.getUsers(callback)
    }
}