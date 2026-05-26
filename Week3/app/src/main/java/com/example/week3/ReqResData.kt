package com.example.week3

import com.google.gson.annotations.SerializedName

data class SingleUserResponse(
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