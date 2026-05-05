package com.example.week2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.week2.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var followingAdapter: FollowingAdapter

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
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = ApiClient.service.getUser(userId)
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
            } catch (e: Exception) {
                Log.e("ProfileFragment", "Error fetching profile", e)
            }
        }
    }

    private fun fetchFollowingList() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = ApiClient.service.getUsers(page = 1)
                if (response.isSuccessful) {
                    response.body()?.data?.let { users ->
                        followingAdapter.submitList(users)
                        binding.tvFollowingTitle.text = "팔로잉 (${users.size})"
                    }
                }
            } catch (e: Exception) {
                Log.e("ProfileFragment", "Error fetching users", e)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
