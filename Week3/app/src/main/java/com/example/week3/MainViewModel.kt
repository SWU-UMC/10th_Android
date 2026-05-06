package com.example.week3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : ViewModel() {
    private val _productList = MutableLiveData<List<ProductData>>()
    val productList: LiveData<List<ProductData>> = _productList

    private val _userProfile = MutableLiveData<UserData>()
    val userProfile: LiveData<UserData> = _userProfile

    private val _userList = MutableLiveData<List<UserData>>()
    val userList: LiveData<List<UserData>> = _userList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchProfileData() {
        viewModelScope.launch {
            try {
                val profileResponse = remoteRepository.getProfile()
                _userProfile.value = profileResponse.data

                val listResponse = remoteRepository.getFollowingList()
                _userList.value = listResponse.data.filter { it.id != 1 }

            } catch (e: Exception) {
            }
        }
    }

    fun fetchLocalProducts() {
        viewModelScope.launch {
            val products = localRepository.getProducts()
            _productList.value = products
        }
    }

    fun toggleWishStatus(productId: Int) {
        viewModelScope.launch {
            val currentList = _productList.value?.toMutableList() ?: return@launch
            val index = currentList.indexOfFirst { it.id == productId }

            if (index != -1) {
                val targetItem = currentList[index]
                currentList[index] = targetItem.copy(isWished = !targetItem.isWished)

                localRepository.saveProducts(currentList)
                _productList.value = currentList
            }
        }
    }
}