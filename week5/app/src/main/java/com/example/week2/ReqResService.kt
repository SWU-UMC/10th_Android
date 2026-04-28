package com.example.week2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResService {
    @GET("api/users/{id}")
    fun getUser(
        @Path("id") id: Int,
        @Header("x-api-key") apiKey: String = BuildConfig.REQRES_API_KEY
    ): Call<UserResponse>

    @GET("api/users")
    fun getUsers(
        @Query("page") page: Int = 1,
        @Header("x-api-key") apiKey: String = BuildConfig.REQRES_API_KEY
    ): Call<UserListResponse>
}
