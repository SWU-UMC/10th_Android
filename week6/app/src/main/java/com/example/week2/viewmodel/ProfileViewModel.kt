package com.example.week2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week2.UserData
import com.example.week2.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userProfile = MutableLiveData<UserData?>()
    val userProfile: LiveData<UserData?> = _userProfile

    private val _followingList = MutableLiveData<List<UserData>>()
    val followingList: LiveData<List<UserData>> = _followingList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchUserProfile(userId: Int) {
        viewModelScope.launch {
            try {
                val response = userRepository.getUser(userId)
                if (response.isSuccessful) {
                    _userProfile.value = response.body()?.data
                } else {
                    _error.value = "Failed to fetch user profile"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            }
        }
    }

    fun fetchFollowingList() {
        viewModelScope.launch {
            try {
                val response = userRepository.getUsers(page = 1)
                if (response.isSuccessful) {
                    _followingList.value = response.body()?.data ?: emptyList()
                } else {
                    _error.value = "Failed to fetch following list"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            }
        }
    }
}
