package com.example.nike.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<ProfileUiState>(ProfileUiState.Loading)
    val uiState: LiveData<ProfileUiState> = _uiState

    fun loadProfile() {
        _uiState.value = ProfileUiState.Loading
        viewModelScope.launch {
            repository.getProfile(MY_USER_ID)
                .onSuccess { profile ->
                    Log.d(TAG, "profile loaded: $profile")
                    _uiState.value = ProfileUiState.Success(
                        profile = profile,
                        followingUsers = emptyList(),
                        isFollowingLoading = true
                    )
                    loadFollowingUsers(profile)
                }
                .onFailure { error ->
                    Log.e(TAG, "profile load failed", error)
                    _uiState.value = ProfileUiState.Error(
                        error.message ?: "프로필 정보를 불러오지 못했습니다."
                    )
                }
        }
    }

    private suspend fun loadFollowingUsers(profile: ProfileUserUiModel) {
        repository.getFollowingUsers(page = 1)
            .onSuccess { users ->
                val following = users.filterNot { it.id == profile.id }
                Log.d(TAG, "following loaded: ${following.size}")
                _uiState.value = ProfileUiState.Success(
                    profile = profile,
                    followingUsers = following,
                    isFollowingLoading = false
                )
            }
            .onFailure { error ->
                Log.e(TAG, "following load failed", error)
                _uiState.value = ProfileUiState.Success(
                    profile = profile,
                    followingUsers = emptyList(),
                    isFollowingLoading = false
                )
            }
    }

    companion object {
        private const val TAG = "ProfileViewModel"
        private const val MY_USER_ID = 1
    }
}

sealed interface ProfileUiState {
    data object Loading : ProfileUiState
    data class Success(
        val profile: ProfileUserUiModel,
        val followingUsers: List<ProfileUserUiModel>,
        val isFollowingLoading: Boolean
    ) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}

class ProfileViewModelFactory(
    private val repository: ProfileRepository = ProfileRepository()
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}