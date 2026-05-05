package com.example.week2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResService {
    @GET("api/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int,
        @Header("x-api-key") apiKey: String = BuildConfig.REQRES_API_KEY
    ): Response<UserResponse>

    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Header("x-api-key") apiKey: String = BuildConfig.REQRES_API_KEY
    ): Response<UserListResponse>
}
