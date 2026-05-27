package com.example.week9.network

import com.example.week9.model.UserProfileUiModel
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

object ReqResUserClient {
    private const val BASE_URL = "https://reqres.in/api/users"
    private const val API_KEY = "reqres_4b7b1e3a525043a4b967f4b1085f63ff"

    suspend fun fetchUser(userId: Int): UserProfileUiModel = withContext(Dispatchers.IO) {
        val connection = (URL("$BASE_URL/$userId").openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = 10_000
            readTimeout = 10_000
            setRequestProperty("Accept", "application/json")
            setRequestProperty("x-api-key", API_KEY)
        }

        try {
            val stream = if (connection.responseCode in 200..299) {
                connection.inputStream
            } else {
                connection.errorStream
            }
            val body = stream.bufferedReader().use { it.readText() }

            if (connection.responseCode !in 200..299) {
                error("ReqRes 요청 실패: ${connection.responseCode}")
            }

            val data = JSONObject(body).getJSONObject("data")
            UserProfileUiModel(
                id = data.getInt("id"),
                email = data.getString("email"),
                firstName = data.getString("first_name"),
                lastName = data.getString("last_name"),
                avatarUrl = data.getString("avatar"),
            )
        } finally {
            connection.disconnect()
        }
    }
}

