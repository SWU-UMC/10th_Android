package com.example.nike.data.repository

import com.example.nike.data.remote.api.ReqResService
import com.example.nike.data.remote.dto.ReqResUserDto
import com.example.nike.domain.repository.ProfileRepository
import com.example.nike.ui.profile.ProfileUserUiModel
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val service: ReqResService
) : ProfileRepository {

    override suspend fun getProfile(userId: Int): Result<ProfileUserUiModel> {
        return runCatching {
            val response = service.getUser(userId)
            val body = response.body()
            if (!response.isSuccessful || body == null) {
                throw IllegalStateException("HTTP ${response.code()}: ${response.message()}")
            }
            body.data.toUiModel()
        }
    }

    override suspend fun getFollowingUsers(page: Int): Result<List<ProfileUserUiModel>> {
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
