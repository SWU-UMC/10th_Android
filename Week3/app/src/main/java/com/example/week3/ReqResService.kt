package com.example.week3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqResService {
    @GET("api/users/1")
    fun getProfile(): Call<SingleUserResponse>

    @GET("api/users")
    fun getFollowingList(@Query("page") page: Int = 1): Call<UserListResponse>
}