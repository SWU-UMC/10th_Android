package com.example.week2.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResService {
    @GET("api/users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<UserResponse>

    @GET("api/users")
    suspend fun getUsers(@Query("page") page: Int = 1): Response<UserListResponse>
}
