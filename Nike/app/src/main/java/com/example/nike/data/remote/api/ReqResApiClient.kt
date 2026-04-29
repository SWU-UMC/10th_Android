package com.example.nike.data.remote.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ReqResApiClient {
    private const val BASE_URL = "https://reqres.in/api/"
    private const val API_KEY = "reqres_4b7b1e3a525043a4b967f4b1085f63ff"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val authenticationInterceptor = AuthenticationInterceptor {
        API_KEY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authenticationInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    val service: ReqResService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ReqResService::class.java)
}