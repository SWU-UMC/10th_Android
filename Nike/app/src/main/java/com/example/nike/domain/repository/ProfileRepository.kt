package com.example.nike.domain.repository

import com.example.nike.ui.profile.ProfileUserUiModel

interface ProfileRepository {
    suspend fun getProfile(userId: Int): Result<ProfileUserUiModel>
    suspend fun getFollowingUsers(page: Int = 1): Result<List<ProfileUserUiModel>>
}
