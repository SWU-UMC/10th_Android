package com.example.week2.repository

import com.example.week2.ReqResService
import com.example.week2.UserResponse
import com.example.week2.UserListResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val reqResService: ReqResService
) {
    suspend fun getUser(id: Int): Response<UserResponse> {
        return reqResService.getUser(id)
    }

    suspend fun getUsers(page: Int): Response<UserListResponse> {
        return reqResService.getUsers(page)
    }
}
