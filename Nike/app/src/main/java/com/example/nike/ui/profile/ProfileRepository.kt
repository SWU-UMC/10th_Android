package com.example.nike.ui.profile

import com.example.nike.data.remote.api.ReqResApiClient
import com.example.nike.data.remote.api.ReqResService
import com.example.nike.data.remote.dto.ReqResUserDto

class ProfileRepository(
    private val service: ReqResService = ReqResApiClient.service
) {

    suspend fun getProfile(userId: Int): Result<ProfileUserUiModel> {
        return runCatching {
            val response = service.getUser(userId)
            val body = response.body()
            if (!response.isSuccessful || body == null) {
                throw IllegalStateException("HTTP ${response.code()}: ${response.message()}")
            }
            body.data.toUiModel()
        }
    }

    suspend fun getFollowingUsers(page: Int = 1): Result<List<ProfileUserUiModel>> {
        return runCatching {
            val response = service.getUsers(page)
            val body = response.body()
            if (!response.isSuccessful || body == null) {
                throw IllegalStateException("HTTP ${response.code()}: ${response.message()}")
            }
            body.data.map { it.toUiModel() }
        }
    }

    private fun ReqResUserDto.toUiModel(): ProfileUserUiModel {
        return ProfileUserUiModel(
            id = id,
            name = "$firstName $lastName",
            email = email,
            avatarUrl = avatar
        )
    }
}

data class ProfileUserUiModel(
    val id: Int,
    val name: String,
    val email: String,
    val avatarUrl: String
)