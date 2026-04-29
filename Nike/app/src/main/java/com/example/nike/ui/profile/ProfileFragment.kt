package com.example.nike.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nike.R
import com.example.nike.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels { ProfileViewModelFactory() }
    private val followingAdapter = FollowingUserAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        setupFollowingList()
        observeProfile()
        viewModel.loadProfile()
    }

    private fun setupFollowingList() {
        binding.rvFollowingUsers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFollowingUsers.adapter = followingAdapter
    }

    private fun observeProfile() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                ProfileUiState.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.tvProfileError.isVisible = false
                }
                is ProfileUiState.Success -> {
                    binding.progressBar.isVisible = false
                    binding.tvProfileError.isVisible = false
                    bindProfile(state.profile)
                    binding.tvFollowingTitle.text = if (state.isFollowingLoading) {
                        "팔로잉 불러오는 중"
                    } else {
                        "팔로잉 (${state.followingUsers.size})"
                    }
                    followingAdapter.submitList(state.followingUsers)
                }
                is ProfileUiState.Error -> {
                    binding.progressBar.isVisible = false
                    binding.tvProfileError.isVisible = true
                    binding.tvProfileError.text = state.message
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun bindProfile(profile: ProfileUserUiModel) {
        binding.tvNickname.text = profile.name
        Glide.with(this)
            .load(profile.avatarUrl)
            .placeholder(R.drawable.profile_avatar_placeholder)
            .error(R.drawable.profile_avatar_placeholder)
            .circleCrop()
            .into(binding.ivProfileAvatar)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvFollowingUsers.adapter = null
        _binding = null
    }
}