package com.example.week9.model

data class UserProfileUiModel(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatarUrl: String,
) {
    val displayName: String = "$firstName $lastName"
}
