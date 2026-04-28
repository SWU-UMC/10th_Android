package com.example.week2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.week2.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var followingAdapter: FollowingAdapter

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ReqResService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        fetchUserProfile(1)
        fetchFollowingList()
    }

    private fun setupRecyclerView() {
        followingAdapter = FollowingAdapter()
        binding.rvFollowing.apply {
            adapter = followingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun fetchUserProfile(userId: Int) {
        service.getUser(userId).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { user ->
                        val fullName = "${user.firstName} ${user.lastName}"
                        binding.tvProfileNickname.text = fullName
                        
                        Glide.with(this@ProfileFragment)
                            .load(user.avatar)
                            .circleCrop()
                            .into(binding.ivProfileImg)
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("ProfileFragment", "Error fetching profile", t)
            }
        })
    }

    private fun fetchFollowingList() {
        service.getUsers(page = 1).enqueue(object : Callback<UserListResponse> {
            override fun onResponse(call: Call<UserListResponse>, response: Response<UserListResponse>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { users ->
                        followingAdapter.setUsers(users)
                        binding.tvFollowingTitle.text = "팔로잉 (${users.size})"
                    }
                }
            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                Log.e("ProfileFragment", "Error fetching users", t)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
