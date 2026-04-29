package com.example.nike.data.remote.api

import com.example.nike.data.remote.dto.ReqResSingleUserResponse
import com.example.nike.data.remote.dto.ReqResUserListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResService {

    @GET("users/{userId}")
    suspend fun getUser(
        @Path("userId") userId: Int
    ): Response<ReqResSingleUserResponse>

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int = 1
    ): Response<ReqResUserListResponse>
}