package com.example.week3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("api/users/{id}")
    fun getUser(
        @Header("x-api-key") apiKey: String,
        @Path("id") id: Int
    ): Call<UserResponse>

    @GET("api/users")
    fun getUsers(
        @Header("x-api-key") apiKey: String,
        @Query("page") page: Int = 1
    ): Call<UserListResponse>
}