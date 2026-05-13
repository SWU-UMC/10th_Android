package com.example.nike.data.remote.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(
    private val apiKeyProvider: () -> String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = apiKeyProvider()
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", apiKey)
            .build()

        Log.d(
            "RETROFIT",
            "AuthenticationInterceptor - intercept() called / request header: ${request.headers}"
        )
        return chain.proceed(request)
    }
}