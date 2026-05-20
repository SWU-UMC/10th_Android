package com.example.week3

import androidx.lifecycle.*
import com.example.week3.data.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val repository: ShoppingRepository
) : ViewModel() {

    val allProducts = repository.getAllProducts().asLiveData()
    val wishlist = repository.getWishlist().asLiveData()

    private val _userData = MutableLiveData<UserInfo?>()
    val userData: LiveData<UserInfo?> get() = _userData

    private val _followingList = MutableLiveData<List<UserInfo>>()
    val followingList: LiveData<List<UserInfo>> get() = _followingList

    fun toggleWishlist(product: Product) {
        viewModelScope.launch {
            repository.toggleWishlist(product)
        }
    }

    fun loadMyData(userId: Int) {
        repository.getUser(userId, object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    _userData.value = response.body()?.data
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {}
        })
    }

    fun loadFollowingData() {
        repository.getUsers(object : Callback<UserListResponse> {
            override fun onResponse(call: Call<UserListResponse>, response: Response<UserListResponse>) {
                if (response.isSuccessful) {
                    _followingList.value = response.body()?.data ?: listOf()
                }
            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {}
        })
    }
}