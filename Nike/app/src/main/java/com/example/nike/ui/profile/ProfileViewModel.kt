package com.example.nike.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nike.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

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
                        error.message ?: "Failed to load profile."
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
