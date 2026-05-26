package com.example.week7.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable data object Home : Route
    @Serializable data object BuyGraph : Route {
        @Serializable data object All : Route
        @Serializable data object Tops : Route
        @Serializable data object Shoes : Route
    }
    @Serializable data object Wishlist : Route
    @Serializable data object ShoppingCart : Route
    @Serializable data object Profile : Route
}