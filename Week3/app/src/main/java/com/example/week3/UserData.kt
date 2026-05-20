package com.example.week3

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val data: UserInfo
)

data class UserListResponse(
    val data: List<UserInfo>
)

data class UserInfo(
    val id: Int,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val avatar: String
)