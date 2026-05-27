package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

data class UserResponse(val data: UserData)

data class UserListResponse(val data: List<UserData>)

data class UserData(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)

interface ReqResService {

    @GET("api/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int,
        @Header("x-api-key")
        apiKey: String = "reqres_3fd0bc2b94a34359a2e2b56a6f1f7d85"
    ): UserResponse

    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int = 2,
        @Header("x-api-key")
        apiKey: String = "reqres_3fd0bc2b94a34359a2e2b56a6f1f7d85"
    ): UserListResponse
}

object RetrofitClient {

    private const val BASE_URL = "https://reqres.in/"

    val service: ReqResService by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReqResService::class.java)
    }
}