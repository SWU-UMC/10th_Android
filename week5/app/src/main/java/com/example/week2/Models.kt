package com.example.week2

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val data: UserData
)

data class UserListResponse(
    val data: List<UserData>
)

data class UserData(
    val id: Int,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val avatar: String
)
