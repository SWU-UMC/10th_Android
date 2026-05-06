package com.example.week3

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val reqResService: ReqResService
) {
    suspend fun getProfile() = reqResService.getProfile()

    suspend fun getFollowingList(page: Int = 1) = reqResService.getFollowingList(page)
}