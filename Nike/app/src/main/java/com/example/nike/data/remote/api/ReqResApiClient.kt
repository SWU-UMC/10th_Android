package com.example.nike.data.remote.api

import com.example.nike.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ReqResApiClient {
    private const val BASE_URL = "https://reqres.in/api/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val authenticationInterceptor = AuthenticationInterceptor {
        BuildConfig.REQRES_API_KEY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authenticationInterceptor)
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor)
            }
        }
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