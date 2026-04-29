package com.example.week2final

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.week3.FollowerAdapter
import com.example.week3.RetrofitClient
import com.example.week3.UserListResponse
import com.example.week3.UserResponse
import com.example.week3.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    private val MY_API_KEY = "reqres_3fd0bc2b94a34359a2e2b56a6f1f7d85"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        loadMyData(1)

        loadFollowingData()
    }

    private fun loadMyData(userId: Int) {
        RetrofitClient.instance.getUser(MY_API_KEY, userId).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val user = response.body()?.data
                    user?.let {
                        binding.tvMyName.text = "${it.firstName} ${it.lastName}"
                        Glide.with(this@ProfileFragment)
                            .load(it.avatar)
                            .into(binding.ivMyProfile)
                    }
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("API_ERROR", "내 정보 로드 실패: ${t.message}")
            }
        })
    }

    private fun loadFollowingData() {
        RetrofitClient.instance.getUsers(MY_API_KEY).enqueue(object : Callback<UserListResponse> {
            override fun onResponse(call: Call<UserListResponse>, response: Response<UserListResponse>) {
                if (response.isSuccessful) {
                    val list = response.body()?.data ?: listOf()

                    // 어댑터 연결
                    binding.rvFollowing.adapter = FollowerAdapter(list)
                    binding.rvFollowing.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                }
            }
            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                Log.e("API_ERROR", "팔로잉 목록 로드 실패: ${t.message}")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}