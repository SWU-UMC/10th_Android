package com.example.week8.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable
    data object Home : Route

    @Serializable
    data object Shop : Route

    @Serializable
    data object Wishlist : Route

    @Serializable
    data object Cart : Route

    @Serializable
    data object Profile : Route

    @Serializable
    data class ProductDetail(val productId: Int) : Route
}
