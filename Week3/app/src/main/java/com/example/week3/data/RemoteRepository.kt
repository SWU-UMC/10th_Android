package com.example.week3.data

import com.example.week3.UserListResponse
import com.example.week3.UserResponse
import com.example.week3.UserService
import retrofit2.Callback
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val service: UserService
) {

    private val API_KEY = "reqres-free-v1"

    fun getUser(userId: Int, callback: Callback<UserResponse>) {
        service.getUser(API_KEY, userId).enqueue(callback)
    }

    fun getUsers(callback: Callback<UserListResponse>) {
        service.getUsers(API_KEY).enqueue(callback)
    }
}