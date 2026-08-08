package com.example.week2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.week2.databinding.FragmentProfileBinding
import com.example.week2.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()
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
        observeViewModel()
        
        if (viewModel.userProfile.value == null) {
            viewModel.fetchUserProfile(1)
        }
        if (viewModel.followingList.value == null) {
            viewModel.fetchFollowingList()
        }
    }

    private fun setupRecyclerView() {
        followingAdapter = FollowingAdapter()
        binding.rvFollowing.apply {
            adapter = followingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observeViewModel() {
        viewModel.userProfile.observe(viewLifecycleOwner) { user ->
            user?.let {
                val fullName = "${it.firstName} ${it.lastName}"
                binding.tvProfileNickname.text = fullName
                
                Glide.with(this@ProfileFragment)
                    .load(it.avatar)
                    .circleCrop()
                    .into(binding.ivProfileImg)
            }
        }

        viewModel.followingList.observe(viewLifecycleOwner) { users ->
            followingAdapter.submitList(users)
            binding.tvFollowingTitle.text = "팔로잉 (${users.size})"
        }

        viewModel.error.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
