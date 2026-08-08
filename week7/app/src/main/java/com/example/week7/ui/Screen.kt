package com.example.week7.ui

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object Home : Screen

    @Serializable
    data object Shop : Screen

    @Serializable
    data object Wishlist : Screen

    @Serializable
    data object Cart : Screen

    @Serializable
    data object Profile : Screen
}
