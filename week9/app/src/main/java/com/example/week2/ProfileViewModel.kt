package com.example.week2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week2.data.ApiClient
import com.example.week2.data.UserData
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val user: UserData, val following: List<UserData>) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val api = ApiClient.service
                val userDef = async { api.getUser(1) }
                val page1Def = async { api.getUsers(1) }

                val userBody = userDef.await().body()?.data
                val page1 = page1Def.await().body()?.data ?: emptyList()

                if (userBody != null) {
                    val following = page1.filter { it.id != userBody.id }
                    _uiState.value = UiState.Success(userBody, following)
                } else {
                    _uiState.value = UiState.Error("유저 정보를 불러오지 못했습니다")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "알 수 없는 오류")
            }
        }
    }
}
