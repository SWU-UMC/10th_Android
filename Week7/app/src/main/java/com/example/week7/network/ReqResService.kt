package com.example.week7.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ReqResService {
    @GET("api/users/1")
    suspend fun getProfile(): SingleUserResponse

    @GET("api/users")
    suspend fun getFollowingList(@Query("page") page: Int = 1): UserListResponse
}